package com.iting.notice.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("teacher/notice/ltnoticeList")
	public String getLtNoticeList(Model model, LtNoticeVO vo) {
		model.addAttribute("ltnoticeList", ltnoticeService.getLtNoticeList(vo));
		return "teacher/notice/ltnoticeList";
	}
	
	//회원용 강의 공지사항 목록
	@GetMapping("member/notice/mltnoticeList")
	public String getMltNoticeList(Model model, LtNoticeVO vo) {
		model.addAttribute("mltnoticeList",ltnoticeService.getMltNoticeList(vo));
		return "member/notice/mltnoticeList";
	}
	
	 //회원용 상세 공지사항
	  @GetMapping("member/notice/mltnoticeInfo/{ltNoticeNum}") 
	  public String mltnoticeInfo(@PathVariable String ltNoticeNum, Model model){
	  model.addAttribute("lno",ltnoticeService.getMltNoticeInfo(ltNoticeNum));
	  ltnoticeService.getMltNoticeInfo(ltNoticeNum);
	  return "member/notice/mltnoticeInfo"; 
	  }
	 
	
	//강의공지 등록페이지
	@GetMapping("teacher/notice/insert")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("teacher/notice/insert");
		return mv;
	}
	//등록기능
	@ResponseBody
	@PostMapping("teacher/notice/insert")
	public String ltNoticeInsert(@RequestBody LtNoticeVO vo){
		
		ltnoticeService.ltNoticeInsert(vo);
		return "redirect:/teacher/notice/ltnoticeList";
	}
	
	//단건조회
	@GetMapping("teacher/notice/info/{ltNoticeNum}")
	public String info(@PathVariable String ltNoticeNum, Model model) {
		model.addAttribute("lno",ltnoticeService.getLtNoticeInfo(ltNoticeNum));
		ltnoticeService.getLtNoticeInfo(ltNoticeNum);
		return "teacher/notice/info";
	}
	
}
