package com.iting.review.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.common.model.PagingVO;
import com.iting.lecture.service.LectureService;
import com.iting.review.model.RSearchVO;
import com.iting.review.model.ReviewVO;
import com.iting.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	@Autowired
	LectureService lectureService;

	// list 전체 조회.
	@RequestMapping("/member/review/list")
	public String reviewList(Model model, ReviewVO vo, RSearchVO svo, PagingVO pvo) {
		
		model.addAttribute("reviewList", reviewService.getReviewList(vo));
		return "member/review/list";

	}
}
