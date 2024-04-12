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
	if(data.tlsnStCd == "j1") {
		confirmAlert("수강신청이 완료되었습니다.", "내 강의실 - 강의목록에서 수강을 시작해보세요!")
	} else {
		errorAlert("수강이 종료된 강의입니다.", "재수강이 불가합니다.")
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
	async function logout(){
		//nlogout();
		console.log("로그아웃 작동");
        await csrf_axios({
            method: 'POST',
            url: '/logout'
        })
            .catch(err => console.log(err));
	}
	
//네이버 로그아웃
function nlogout(){
	let naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "IUFmLIwupwmkJxh3eacH"
		}
	);
		naverLogin.logout(); // 네이버 로그아웃 처리
}