package com.iting.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.iting.test.model.TestVO;
import com.iting.test.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	TestService testService;
	
	// 강의조회
//	@RequestMapping("test/list")
//	public String getTestList(Model model, TestVO vo) {
//		model.addAttribute("testList", testService.getTestList());
//		return "teacher/test/list";
//	}
	
	// 문제조회
	@RequestMapping("test/test")
	public String getTestList(Model model, TestVO vo) {
		model.addAttribute("testList", testService.getTestList());
		return "teacher/test/test";
	}
	
	// 등록페이지 이동
	@GetMapping("test/insert")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("teacher/test/insert");
		return mv;
	}

	// 등록처리
	@PostMapping("test/insert")
	public String insertTest(TestVO vo) {
		testService.insertTest(vo);
		int result = testService.insertTest(vo);
		if (result > 0) {
			System.out.println("등록이 완료되었습니다.");
		}
		return "redirect:test/list";
	}
}


