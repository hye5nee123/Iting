package com.iting.lecture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iting.lecture.model.LectureVO;
import com.iting.lecture.service.LectureService;

@Controller
public class LectureController {
	@Autowired
	LectureService lectureService;
	
	// 목록조회 - 예시
	@RequestMapping("admin/main")
	public ModelAndView getLectureList() {
		ModelAndView mv  = new ModelAndView();
		
		//뷰페이지 지정
		mv.setViewName("admin/main");		
		return mv;
	}
	
	/* 회원 */
	// 강의 목록 조회
	
	
	// 강의 단건 조회
	@GetMapping("lecture/info/{ltNum}")
	public String info(@PathVariable String ltNum, Model model) {
		model.addAttribute("lecture", lectureService.getLectureInfo(ltNum));
		return "member/lecture/info";
	}
	
	
	/* 강사 */
	// 강의 단건 조회
	
	// 강의 수정
	
	// 강의 등록
	
	
	/* 관리자 */
	// 강의 수정
	
	//
}
