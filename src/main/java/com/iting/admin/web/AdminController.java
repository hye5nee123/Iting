package com.iting.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iting.admin.model.AdminVO;
import com.iting.admin.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	
	// 목록조회
	@RequestMapping("admin/main")
	public ModelAndView getTestList(AdminVO vo) {
		ModelAndView mv  = new ModelAndView();
		
		mv.addObject("testList", adminService.getTestList(vo));
		
		//뷰페이지 지정
		mv.setViewName("admin/main");		
		return mv;
	}
}
