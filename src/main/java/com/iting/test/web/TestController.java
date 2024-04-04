package com.iting.test.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	// 목록조회
	@RequestMapping("teacher/test/list")
	public String getTestList(Model model, TestVO vo) {
		model.addAttribute("testList", testService.getTestList());
		return "teacher/test/test";
	}
	
	// 등록페이지 이동
	@GetMapping("teacher/test/insert")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("teacher/test/insert");
		return mv;
	}

	// 등록처리
	@ResponseBody
	@PostMapping("teacher/test/insert")
	public TestVO insertTest(@RequestBody TestVO vo) {
		vo.setLtNum("lt00001");
		testService.insertTest(vo);
		return vo;
	}
	
	// 수정페이지 이동
	@GetMapping("teacher/test/update/{prblNum}")
	public String update(@PathVariable String prblNum, Model model) {
		model.addAttribute("test", testService.getTestInfo(prblNum));
		return "teacher/test/update";
	}
		
	// 수정처리
	@ResponseBody
	@PostMapping("teacher/test/update/{prblNum}")
	public String update(@RequestBody TestVO vo) {
		testService.updateTest(vo);
		return "redirect:teacher/test/list";
	}
			
	// 삭제처리
	@RequestMapping("teacher/test/delete/{prblNum}")
	public String delete(@PathVariable String prblNum){ 
		System.out.println("prblNum : " + prblNum);
		testService.deleteTest(prblNum);
		return "redirect:/teacher/test/list";
	}
}