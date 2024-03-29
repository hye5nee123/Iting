package com.example.iting.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.iting.test.TestVO;
import com.example.iting.test.service.TestService;

@Controller
public class TestController {

	@Autowired
	TestService testService;
	
	// 목록조회
	@RequestMapping("/index")
	public ModelAndView getTestList(TestVO vo) {
		ModelAndView mv  = new ModelAndView();
		
		mv.addObject("testList", testService.getTestList(vo));
		
		//뷰페이지 지정
		mv.setViewName("index");		
		return mv;
	}
}
