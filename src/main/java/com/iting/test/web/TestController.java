package com.iting.test.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.test.model.TestVO;
import com.iting.test.service.TestService;

@Controller
public class TestController {

	@Autowired
	TestService testService;

	// 목록조회
	@RequestMapping("test/list")
	public String getTestList(Model model, TestVO vo) {
		model.addAttribute("cnqList", testService.getTestList(vo));
		return "teacher/test/list";
	}



}
