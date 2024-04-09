let actype = ''; // 회원 강사 구분용

usertypeForm(1); // 첫 화면 폼 그려주기

let allboxs = document.querySelectorAll('.chk');
let chkdboxs = document.querySelectorAll('.chk:checked');
for (box of allboxs) {
	box.addEventListener('click', () => {
		if (allboxs.length != chkdboxs.length) {
			document.querySelector('#chk_all').checked = false;
		}
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
function usertypeForm(num) {
	let form;
	document.getElementsByClassName('radios')[0].innerHTML = ''; // 바뀔때 비워주기용
	if (num == '1') {
		actype = 'b1';
		form = `<div class="checkout__input">
					<p>관심사</p>
				</div>
                <select id="ocmenu" name="ocmenu">
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
		form = `<div class="checkout__input">
				<form action="/accountfile" method="post" enctype="multipart/form-data">
					<p>이력서 첨부 <span>*</span></p>
					</div>
					<input type="file" accept=".pdf, .xlsx, .hwp" id="file" name="file"/>
				</form>
				`;
	}
	document.getElementsByClassName('radios')[0].innerHTML = form;
}


// 유효성 검사
let idAccep = false;
let pwAccep = false;
let mailAccep = false;
let phoneAccep = false;
let SMSAccep = false;
let fileAccep = false;
// 변수 담기
let idv;
let pwv;
let pwChkv;
let mailv;
let phonev;
let filev;
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
function pwCheck() {
	pwv = pw.value;
	pwChkv = pwchk.value;
	let statPw = ``;
	let statPwChk = ``;
	pwAccep = false;
	if (pwv == '') {
		statPw = `<p>비밀번호를 입력해주세요</p>`;
	} else if (!(/^[a-zA-Z0-9]{8,16}$/.test(pwv))) {
		statPw = `<p>영문 대소문자, 숫자조합 8~16자로 입력해주세요</p>`;
	} else if (pwv == pwChkv) {
		statPwChk = `<p>일치</p>`;
		pwAccep = true;
	} else if (pwChkv != '') {
		statPwChk = `<p>비밀번호가 일치하지 않습니다</p>`;
	}
	document.getElementsByClassName('pwchkstr')[0].innerHTML = statPw;
	document.getElementsByClassName('pwchkchkstr')[0].innerHTML = statPwChk;
}
function mailCheck() {
	mailv = email.value;
	let statMail = ``;
	mailAccep = false;
	if (mailv == '') {
		statMail = `<p>이메일을 입력해주세요</p>`;
	} else if (!(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(mailv))) {
		statMail = `<p>이메일 형식이 맞지 않습니다.</p>`;
	} else {
		mailAccep = true;
	}
	document.getElementsByClassName('mailchkstr')[0].innerHTML = statMail;
}
function phoneCheck() {
	phonev = phone.value;
	let statphone = ``;
	phoneAccep = false;
	if (phonev == '') {
		statphone = `<p>휴대전화번호를 입력해주세요</p>`;
	} else if (!(/^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/.test(phonev))) {
		statphone = `<p>휴대전화번호 형식이 맞지 않습니다.</p>`;
	} else if (!SMSAccep) {
		statphone = `<p>인증 필요</p>`;
		phoneAccep = true;
	}
	document.getElementsByClassName('phonechkstr')[0].innerHTML = statphone;
}
function fileCheck() {
	filev = file.value;
	let statfile = ``;
	fileAccep = false;
	if (filev == '') {
		statfile = `<p>이력서를 첨부해주세요</p>`;
	} else {
		fileAccep = true;
	}
	document.getElementsByClassName('phonechkstr')[0].innerHTML = statfile;
}

function chkAccount() {
	// 유효성검사
	if (idv == '') {
		alert("아이디를 입력해주세요");
		return;
	} else if (!idAccep) {
		alert("아이디가 올바르지 않습니다. 다시 확인해주세요.");
		return;
	}
	if (pwv == '') {
		alert("비밀번호를 입력해주세요");
		return;
	}
	if (pwChkv != pwv) {
		alert("비밀번호가 일치하지 않습니다.");
		return;
	} else if (!pwAccep) {
		alert("비밀번호가 올바르지 않습니다. 다시 확인해주세요.");
		return;
	}
	if (mailv == '') {
		alert("이메일을 입력해주세요");
		return;
	} else if (!mailAccep) {
		alert("이메일 형식이 올바르지 않습니다. 다시 확인해주세요.");
		return;
	}
	if (phonev == '') {
		alert("휴대전화번호를 입력해주세요");
		return;
	} else if (!phoneAccep) {
		alert("휴대전화번호가 올바르지 않습니다. 다시 확인해주세요.");
		return;
	} else if (!SMSAccep) {
		alert("휴대전화번호를 인증해주세요");
		return;
	}
	if (filev && actype == 'd2') {
		alert("이력서를 첨부해주세요");
		return;
	}
	let agrees = document.querySelectorAll('.svc:checked');
	if (agrees.length != 2) {
		alert("필수 약관에 동의해야합니다.");
		return;
	}
	insertAccount();
}
async function insertAccount(){
	let nickv = nick.value;
	let addrv = sample6_address.value;
	let dadrv = sample6_detailAddress.value;
	let logCd = 'a1';
	let token = '';
	let intorfile = '';
	if(actype == 'b1'){
		intorfile = ocmenu.value;
	} else {
		intorfile = file.value;
	}
	let param = {
		idv,
		pwv,
		nickv,
		addrv,
		dadrv,
		mailv,
		phonev,
		logCd,
		token,
		actype,
		intorfile};
	let result = await csrf_axios({
		method: 'post',
		url: '/insertaccount',
		data: param,
	})
		.then(res => res.data);
		if(result == 1){
			if(actype == 'b1'){
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
	 					location.href = "/login";
	  				}
				});
			} else if(actype == 'd2'){
				Swal.fire({
					icon: "success",
					title: "가입신청이 완료되었습니다",
					test: "*검토 후 연락드릴때까지 기다려주세요",
					confirmButtonText: "메인으로",
					confirmButtonColor: "#205cdc"
				}).then((rest) => {
	  				if (rest.isConfirmed) {
						location.href = "/member/main";
					}
				});
			}
		} else {
			errorAlert("회원가입 실패", "관리자에게 문의바랍니다");
		}
	}


