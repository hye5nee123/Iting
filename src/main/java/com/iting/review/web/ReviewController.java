package com.iting.review.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.common.model.PagingVO;
import com.iting.review.model.RSearchVO;
import com.iting.review.model.ReviewVO;
import com.iting.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	// list 전체 조회.
	@RequestMapping("/member/review/list")
	public String reviewList(Model model, ReviewVO vo, RSearchVO svo, PagingVO pvo) {
		// paging처리.
		pvo.setPageUnit(5); // 데이터수
		pvo.setPageSize(3); // 페이지번호
		svo.setStart(pvo.getFirst());
		svo.setEnd(pvo.getLast());
		System.out.println(">>>>>>>>>>>> " + vo + ":" + svo);
		Map<String, Object> map = reviewService.getReviewList(vo, svo);
		pvo.setTotalRecord((long) map.get("count"));
		model.addAttribute("paging", pvo);
		model.addAttribute("reviewList", map.get("data"));
		return "member/review/list";

	}
}
