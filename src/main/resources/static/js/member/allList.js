/**
 * allList.js
 */

// 즉시 실행함수
var lectureService = (function(){

	// 강의 전체목록 요청
	function ltListReq() {
		axios.get("/member/lecture/allSelect")
			 .then(res => ltListRes(res.data))
	}
	
	// 강의 전체목록 응답
	function ltListRes(data) {
		data.forEach(item => {
			console.log(item)
		})
	}

	
	// 리스트 응답
	function listRes(res) {
		//console.log(res)
		// 목록 출력
		res.data.forEach((item, idx) => {
			// console.log(item)
			empList.innerHTML += makeTr(item, idx);
		})
		// 페이징
		navs.innerHTML = makePage(res.paging);
	}
	
	function makeDiv(obj, idx) {
		
		let newTag = 
			``;
		return newTag;
	}
	
	
	
	return {ltListReq}
	
})();