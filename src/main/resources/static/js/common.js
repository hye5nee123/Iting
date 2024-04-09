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
        //e.preventDefault();
		console.log("로그아웃 작동");
        csrf_axios({
            method: 'POST',
            url: '/logout'
        })
            .catch(err => console.log(err));
	}
	
/* 파일업로드 ajax */
function uploadFileReq(typCode) {
	let param = new FormData(document.imgForm);
	console.log(param);
	axios({
            method: 'POST',
            url: `/upload/file?fileCode=${typCode}`,
            data : param,
            headers: {
				[header]: token
			}
        })
        .then(res => uploadFileRes(res.data));
}
function uploadFileRes(retCode) {
	console.log(retCode)
	if(retCode > 0) {
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

/* (5) 현재 로그인 상태를 확인 */
			window.addEventListener('load', function () {
				naverLogin.getLoginStatus(function (status) {
					if (status) {
						/* (6) 로그인 상태가 "true" 인 경우 사용자 정보를 출력합니다. */
						console.log(naverLogin.user);
						setLoginStatus();
					}
				});
			});

			/* (6) 로그인 상태가 "true" 인 경우 사용자 정보를 출력합니다. */
			function setLoginStatus() {
				let naverId = naverLogin.user.getEmail();
				// let nickName = naverLogin.user.get();
				/* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
				$("#gnbLogin").click(function (e) {
					e.preventDefault();
					naverLogin.logout();
					location.replace('/member/main');
				});
			}