let actype = ''; // 회원 강사 구분용
let idAccep = false;
let fileAccep = false;
let idv;
let filev;
let pwFormOn = true;
let phoneFormOn = true;
async function idChk() {
	idAccep = false;
	idv = id.value;
	let idStat;
	if (idv == '') {
		idStat = `<p>아이디를 입력해주세요</p>`;
	} else if (!(/^[a-zA-Z0-9]{4,20}$/.test(idv))) {
		idStat = `<p>영문 대소문자, 숫자조합 4~20자로 입력해주세요</p>`;
	} else {
		await axios.get(`/common/idchk/${idv}`)
			.then(res => {
				if (res.data.id != null || res.data.id == idv) {
					idStat = `<p>사용할 수 없는 아이디입니다. 다른 아이디를 입력해주세요<p>`
				} else {
					idStat = `<p>사용가능한 아이디입니다</p>`;
					idAccep = true;
				}
			})
			.catch(err => console.log(err));

	}
	document.getElementsByClassName('idchk')[0].innerHTML = idStat;
}
function fileCheck() {
	filev = uFile.files[0];
	let statfile = ``;
	fileAccep = false;
	if (filev == null || filev == '') {
		statfile = `<p>이력서를 첨부해주세요</p>`;
	} else {
		fileAccep = true;
	}
	document.getElementsByClassName('filechkstr')[0].innerHTML = statfile;
}
usertypeForm(1); // 첫 화면 폼 그려주기
function usertypeForm(num) {
	let form;
	document.getElementsByClassName('radios')[0].innerHTML = ''; // 바뀔때 비워주기용
	if (num == '1') {
		actype = 'b1';
		form = `
				<label for="ocmenu" class="form-label">관심사</label>
                <select class="form-select form-select-sm" id="ocmenu" name="ocmenu">
                    <option value="">선택</option>
                    <option value="c1">프로그래밍 언어</option>
                    <option value="c2">컴퓨터공학 전공</option>
                    <option value="c3">웹 개발</option>
                    <option value="c4">데이터 분석</option>
                    <option value="c5">IT 자격증</option>
                </select>
                    `;
	}
	else if (num == '2') {
		actype = 'd2';
		form = `
				<label for="ocmenu" class="form-label">이력서 첨부 <span class="needs">*</span></label>
				<input class="form-control" type="file" accept=".pdf, .xlsx, .hwp" id="uFile" name="uFile" onchange="fileCheck()"/>
				<div class="filechkstr form-text"></div>
				`;
	}
	document.getElementsByClassName('radios')[0].innerHTML = form;
}

// 약관 체크
let allboxs = document.querySelectorAll('.chk');
for (box of allboxs) {
	box.addEventListener('click', () => {
			document.querySelector('#chk_all').checked = false;
	});
}
chk_all.addEventListener('click', () => {
	for (obj of allboxs) {
		if (chk_all.checked) {
			obj.checked = true;
		} else if (!chk_all.checked) {
			obj.checked = false;
		}
	}
});

//강사 파일 업로드용
const axiosform = axios.create({
	headers: {
		"Content-Type": `multipart/form-data`,
		[header]: token
	},
});

function chkAccount() {
	if (id.value == '') {
		alert("아이디를 입력해주세요");
		return;
	} else if (!idAccep) {
		alert("아이디가 올바르지 않습니다. 다시 확인해주세요.");
		return;
	}
	if(!chkInput()){
		return;
	}
	if (filev == null && actype == 'd2' || filev == '' && actype == 'd2') {
		alert("이력서를 첨부해주세요");
		return;
	}
	let agrees = document.querySelectorAll('.svc:checked');
	if (agrees.length != 2) {
		alert("필수 약관에 동의해야합니다.");
		return;
	}
	let param = setValue();
	if (actype == 'b1') {
		insertb1(param);
	} else if (actype == 'd2') {
		insertd2(param);
	}
}

async function insertb1(param) {
	let result = await csrf_axios({
		method: 'POST',
		url: '/insertMember',
		data: param
	})
		.then(ret => ret.data);

	if (result == 1) {
		Swal.fire({
			icon: "success",
			title: "회원가입이 완료되었습니다",
			showDenyButton: true,
			confirmButtonText: "메인으로",
			denyButtonText: "로그인화면으로",
			confirmButtonColor: "#205cdc"
		}).then((rest) => {
			if (rest.isConfirmed) {
				location.href = "/member/main";
			} else if (rest.isDenied) {
				location.href = "/commonlogin";
			}
		});
	} else {
		errorAlert("회원가입 실패", "관리자에게 문의바랍니다");
	}
}
async function insertd2(param) {
	const formData = new FormData();
	formData.append("vo", new Blob([JSON.stringify(param)], { type: "application/json" }));
	formData.append("fileData", filev);
	let result = await axiosform({
		method: 'POST',
		url: '/insertLecturer',
		data: formData
	})
		.then(ret => ret.data);

	if (result == 2) {
		Swal.fire({
			icon: "success",
			title: "가입신청이 완료되었습니다",
			text: "*검토 후 연락예정",
			confirmButtonText: "메인으로",
			confirmButtonColor: "#205cdc"
		}).then((rest) => {
			if (rest.isConfirmed) {
				location.href = "/member/main";
			}
		});
	} else {
		errorAlert("회원가입 실패", "관리자에게 문의바랍니다");
	}
}


