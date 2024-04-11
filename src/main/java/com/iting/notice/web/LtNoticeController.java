package com.iting.notice.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iting.notice.model.LtNoticeVO;
import com.iting.notice.service.LtNoticeService;

@Controller
public class LtNoticeController {

	@Autowired
	LtNoticeService ltnoticeService;
	
	//강의공지사항 목록
	@GetMapping("/teacher/notice/ltnoticeList")
	public String getLtNoticeList(Model model, LtNoticeVO vo) {
		model.addAttribute("ltnoticeList", ltnoticeService.getLtNoticeList(vo));
		return "teacher/notice/ltnoticeList";
	}
	//강의공지 등록페이지
	@GetMapping("/teacher/notice/insert")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("teacher/notice/insert");
		return mv;
	}
	//등록기능
	@ResponseBody
	@PostMapping("/teacher/notice/insert1")
	public String ltNoticeInsert(@RequestBody LtNoticeVO vo){
		
		ltnoticeService.ltNoticeInsert(vo);
		return "redirect:/teacher/notice/ltnoticeList";
	}
}