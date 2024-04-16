/**
 * 공통js
 */

/* CSRF AXIOS 설정 */

let token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
let header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');


// 인스턴스를 생성할때 config 기본값 설정하기
const csrf_axios = axios.create({
	headers: {
		"Content-Type" : "application/json; charset=utf-8",
		[header]: token
	}
});

/* 파일업로드 ajax */
let fileNum = ""; // 첨부파일번호
let fileNums = []; // 첨부파일번호(여러건)

function uploadFileReq(typCode, formName) {

	let fileForm = new FormData(formName);
	
	axios({
            method: 'POST',
            url: `/upload/files?fileCode=${typCode}`,
            data : fileForm,
            headers: {
				[header]: token
			}
        })
        .then(res => uploadFileRes(res.data));
}
function uploadFileRes(data) {
	//console.log(data.fvo.atchNum);
	//console.log(data.retCode);
	//console.log(data)
	
	console.log("서버로부터 넘어옴", data);
	
	data.forEach((item, idx) => {
		fileNums.push(item.fvo.atchNum);
		
		// 파일 1개일 때 업로드
		if(idx == 0) {
			fileNum = item.fvo.atchNum;
		}
	})
	
	console.log("등록된 첨부파일 번호", fileNums);
	
	
	if(data[0].retCode > 0) {
		confirmAlert("파일 등록이 완료되었습니다.");
	} else {
		errorAlert("파일등록이 실패했습니다", "관리자에게 문의 바랍니다.");
	}
}

/* 수강신청 요청 */
function lectInsertReq(ltNum, memNum) {
	//console.log(ltNum, memNum)
	if(memNum != '' && memNum != null){
		const tlsnStCd = "j1"; // 수강중
		const ceteYnCd = "t2"; // 수료 여부 코드
		
		const param = {tlsnStCd, ltNum, memNum, ceteYnCd}
		
		csrf_axios({
	            method: 'POST',
	            url: "/member/tlsn/insert",
	            data : param
	        })
	        .then(res => lectInsertRes(res.data));
    } else {
    	errorAlert("로그인이 필요합니다.");
    }
}

/* 수강신청 응답 */
function lectInsertRes(data) {
	console.log(data);
	if(data > 0) {
		confirmAlert("수강신청이 완료되었습니다.", "내 강의실 - 강의목록에서 수강을 시작해보세요!")
	} else if(data == -1) {
		// 수강 완료 시 알림
		Swal.fire({
		  title: "이미 수강중인 강의입니다.",
		  text: "내강의실로 이동하시겠습니까?",
		  icon: "info",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "수강하기",
		  cancelButtonText: "취소"
		}).then((result) => {
		  if (result.isConfirmed) {
		    location.href = "/member/tlsn/list";
		  }
		});
	}
	else {
		errorAlert("수강이 완료된 강의입니다.")
	}
}


/* 오늘 날짜 구하기 */
function getToday() {
	let date = new Date();
	let year = date.getFullYear();
	let month = ('0' + (date.getMonth() + 1)).slice(-2);
	let day = ('0' + date.getDate()).slice(-2);
	return `${year}-${month}-${day}`;
}
	
	
/* sweetAlert2 공통함수 */
// 1. x표시 알림창
function errorAlert(tit, txt) {
	Swal.fire({
		  icon: "error",
		  title: tit,
		  text: txt,
		  confirmButtonText: "확인",
		  confirmButtonColor: "#ff8a00"
		});
}

// 2. 체크표시 알림창
function confirmAlert(tit, txt) {
	Swal.fire({
		icon: "success",
		title: tit,
		text: txt,
		confirmButtonText: "확인",
		confirmButtonColor: "#205cdc"
	});
}

// 3. 느낌표 알림창
function infoAlert(tit, txt) {
	Swal.fire({
		icon: "info",
		title: tit,
		text: txt,
		confirmButtonText: "확인",
		confirmButtonColor: "#205cdc"
	});
}

//로그아웃
	function logout(){
		console.log("로그아웃 작동");
        csrf_axios({
            method: 'POST',
            url: '/logout'
        })
            .catch(err => console.log(err));
	}
	
// 다음 주소 api 코드
		function sample6_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							//var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('sample6_postcode').value = data.zonecode;
							document.getElementById("sample6_address").value = addr;
							// 커서를 상세주소 필드로 이동한다.
							document.getElementById("sample6_detailAddress")
									.focus();
						}
					}).open();
		}
		
// SMS전송요청
		async function sendSMS(phonenum){
			if(phoneAccep){
				let numkey = '';
				for(i=0; i<4; i++){
					numkey += Math.floor(Math.random() * 10);
				}
				let param = {phonenum, numkey};
			await csrf_axios({
				method: 'post',
				url: '/sendsms',
				data: param,
				})
				.then(res => console.log("문자 전송"));
				document.getElementsByClassName('smschk')[0].innerHTML =
				`
						<input type="text" id="sms" name="sms" placeholder="인증번호 4자리 입력">
						<button type="button" class="site-btn" onclick="smschk('`+numkey+`')">인증확인</button>`;
			} else {
				alert("휴대전화번호가 올바르지 않습니다")
				return;
			}
		}
		function smschk(num){
			let smschkresult;
			if(phoneAccep && num == sms.value){
				smschkresult = `<p>인증이 완료되었습니다</p>`;
			document.getElementsByClassName('smschk')[0].innerHTML = '';
			sendsms.disabled = true;
				SMSAccep = true;
				phone.readOnly = true;
			} else {
				smschkresult = `<p>인증 실패</p>`;
			}
			document.getElementsByClassName('phonechkstr')[0].innerHTML = smschkresult;
		}		
