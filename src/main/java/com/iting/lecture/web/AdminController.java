package com.iting.lecture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iting.lecture.model.AdminVO;
import com.iting.lecture.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	
	// 목록조회 - 예시
	@RequestMapping("admin/main")
	public ModelAndView getTestList(AdminVO vo) {
		ModelAndView mv  = new ModelAndView();
		
		mv.addObject("testList", adminService.getTestList(vo));
		
		//뷰페이지 지정
		mv.setViewName("admin/main");		
		return mv;
	}
	
	/* 회원 */
	// 강의 목록 조회
	
	
	// 강의 단건 조회
	
	
	
	/* 강사 */
	// 강의 단건 조회
	
	// 강의 수정
	
	// 강의 등록
	
	
	/* 관리자 */
	// 강의 수정
	
	//
}
