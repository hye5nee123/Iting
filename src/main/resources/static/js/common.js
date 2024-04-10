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

//로그아웃
	function logout(){
		naverLogin.logout(); // 네이버 로그아웃 처리
		console.log("로그아웃 작동");
        csrf_axios({
            method: 'POST',
            url: '/logout'
        })
            .catch(err => console.log(err));
	}
	
/* 파일업로드 ajax */
let fileNum = ""; // 첨부파일번호

function uploadFileReq(typCode, formName) {

	let fileForm = new FormData(formName);
	
	axios({
            method: 'POST',
            url: `/upload/file?fileCode=${typCode}`,
            data : fileForm,
            headers: {
				[header]: token
			}
        })
        .then(res => uploadFileRes(res.data));
}
function uploadFileRes(data) {
	console.log(data.fvo.atchNum);
	console.log(data.retCode);
	//console.log(data)
	
	fileNum = data.fvo.atchNum;
	
	if(data.retCode > 0) {
		confirmAlert("파일 등록이 완료되었습니다.");
	} else {
		errorAlert("파일등록이 실패했습니다", "관리자에게 문의 바랍니다.");
	}
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

