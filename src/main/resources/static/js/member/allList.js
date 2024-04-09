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
		//console.log(data.lectList)
		
		listRes(data);
		
	}

	
	// 리스트 응답
	function listRes(data) {
		//console.log(data)
		// 목록 출력
		for(let lecture of data.lectList) {
			lectureList.innerHTML += makeDiv(lecture);
		}
		
		// 페이징
		//navs.innerHTML = makePage(res.paging);
	}
	
	function makeDiv(lecture) {
		
		let newTag = `<div class="col-3">
	                    <div class="product__item">
	                        <div class="product__item__pic set-bg" data-setbg="/img/member/main/html.png">
	                            <ul class="product__hover">
	                                <li><a href="#"><img src="/img/member/main/heart.png" alt="찜하기버튼"></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__item__text">
	                            <h6><span>${lecture.name}</span> 선생님</h6>
	                            <a href="#" class="add-cart">상세보기</a>
	                            <div class="rating">
	                                <i class='bx bxs-star'></i>
									<i class='bx bxs-star'></i>
									<i class='bx bxs-star'></i>
									<i class='bx bxs-star'></i>
									<i class='bx bx-star' ></i>
	                            </div>
	                            <h5>${lecture.ltTtl}</h5>
	                            <span>${lecture.ltCateCd}</span>
	                            <span>${lecture.ltKeywordCd}</span>
	                            
	                        </div>
	                    </div>
	                </div>`;
		return newTag;
	}
	
	
	
	return {ltListReq}
	
})();