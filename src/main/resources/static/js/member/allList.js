/**
 * allList.js
 */

// 즉시 실행함수
var lectureService = (function(){

	// 강의 전체목록 요청
	function ltListReq(page, ltTtl) {
		
		const param = `?page=${page}&ltCateCd=${ltCateCd}&ltTtl=${ltTtl}`;
			
		axios.get("/member/lecture/allSelect" + param)
			 .then(res => ltListRes(res.data))
	}
	
	function gopage(page){
		//frmSearch.submit();
		ltListReq(page);
	}
	
	// 강의 전체목록 응답
	function ltListRes(data) {
		//console.log(data)
		
		listRes(data);
		
	}

	
	// 리스트 응답
	function listRes(data) {
		lectureList.innerHTML = '';
		console.log(data)
		// 목록 출력
		for(let lecture of data.lectList) {
			lectureList.innerHTML += makeDiv(lecture);
		}
		
		// 페이징
		navs.innerHTML = makePage(data.paging);
	}
	
	
	function makeDiv(lecture) {
		
		let newTag = `<div class="col-3">
	                    <div class="product__item">
	                        <div class="product__item__pic">`;
	                        
	                        // 강의 썸네일
	                        if(lecture.ltImg == null) {
	                        	newTag += `<img src="/img/common/lect_sample.jpg" alt="기본 강의썸네일" onclick="location.href='/member/lecture/info/${lecture.ltNum}'">`;
	                        } else {
	                        	newTag += `<img src="/download/${lecture.ltImg}" alt="${lecture.ltImg}" onclick="location.href='/member/lecture/info/${lecture.ltNum}'">`;
	                        }
	                        	                        
	                        newTag += `<ul class="product__hover">
	                                <li><a href="#"><img src="/img/member/main/heart.png" alt="찜하기버튼"></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__item__text lectList">
	                            <span class="lectSpan">${lecture.ltCateCd}</span>
	                            <h6 class="mb-2"><span>${lecture.name}</span> 강사님</h6>
	                            <div class="rating">`;
	          // 별점에 따라 별그리기            
              switch (lecture.reviewAvg) {
				  case 1:
				    newTag += `<i class='bx bxs-star'></i>
							    <i class='bx bx-star'></i>
							    <i class='bx bx-star'></i>
							    <i class='bx bx-star'></i>
							    <i class='bx bx-star'></i>`;
				    break;
				  case 2:
				    newTag += `<i class='bx bxs-star'></i>
							    <i class='bx bxs-star'></i>
							    <i class='bx bx-star'></i>
							    <i class='bx bx-star'></i>
							    <i class='bx bx-star'></i>`;
				    break;
				  case 3:
				    newTag += `<i class='bx bxs-star'></i>
							    <i class='bx bxs-star'></i>
							    <i class='bx bxs-star'></i>
							    <i class='bx bx-star'></i>
							    <i class='bx bx-star'></i>`;
				    break;
				  case 4:
				    newTag += `<i class='bx bxs-star'></i>
							    <i class='bx bxs-star'></i>
								<i class='bx bxs-star'></i>
							    <i class='bx bxs-star'></i>
							    <i class='bx bx-star'></i>`;
				    break;
				  case 5:
				    newTag += `<i class='bx bxs-star'></i>
							    <i class='bx bxs-star'></i>
							    <i class='bx bxs-star'></i>
							    <i class='bx bxs-star'></i>
							    <i class='bx bxs-star'></i>`;
				    break;
				    
				  default:
				    newTag += `<i class='bx bx-star'></i>
	                      	<i class='bx bx-star'></i>
	                      	<i class='bx bx-star'></i>
	                      	<i class='bx bx-star'></i>
	                      	<i class='bx bx-star'></i>`;
							}
	                       
	                      /* 
	                      for(let j = 0; j < lecture.reviewAvg; j++) {
	                      	newTag += `<i class='bx bxs-star'></i>`
	                      }
	                      */
	                      
	                 newTag += `</div>
	                 			<h5 onclick="location.href='/member/lecture/info/${lecture.ltNum}'">${lecture.ltTtl}</h5>
	                            
	                        </div>
	                    </div>
	                </div>`;
		return newTag;
	}
	
	function makePage(paging) {
		console.log(paging)

		let tag = `<nav aria-label="...">
		  <ul class="pagination">`;
		
		// 이전 버튼
		if(paging.startPage > 1) {
			tag += `<li class="page-item">
		        <a class="page-link" href="javascript:gopage(${paging.startPage-1})">Previous</a></li>`;
		}
		// 페이지 번호
		for(i=paging.startPage; i <= paging.endPage; i++) {
			tag += `<li class="page-item">
				<a class="page-link" href="javascript:gopage(${i})">${i}</a></li>`;
		}
		// 다음 버튼
		if(paging.endPage < paging.lastPage) {
			tag += `<li class="page-item">
				<a class="page-link" href="javascript:gopage(${paging.endPage + 1})">Next</a></li>`;
		    tag += `</ul>
		    	</nav>`;
		}
		return tag;
	}
	
	
	return {ltListReq}
	
})();