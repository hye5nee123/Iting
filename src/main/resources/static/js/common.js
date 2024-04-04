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