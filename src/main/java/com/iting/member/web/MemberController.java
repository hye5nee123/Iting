package com.iting.member.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iting.member.model.MemberVO;
import com.iting.member.service.MemberService;
import com.iting.tlsn.service.TlsnService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	TlsnService tlsnService;
	
	// 내강의실
	@RequestMapping("member/myclass/list")
	public String getMemberList(Model model, MemberVO vo, HttpSession session) {
		String user = (String) session.getAttribute("usernum");
		model.addAttribute("testList", memberService.getMemberList());
		model.addAttribute("tlsnList", tlsnService.getTlsnList(user));
		return "member/myclass/list";
	}
	
	// 마이페이지 이동
	@GetMapping("/member/mypage")
	public ModelAndView goMyMain() {
		ModelAndView mv = new ModelAndView("member/mypage/userInfo");
		return mv;
	}
	
	@GetMapping("/member/mypage/sttlList")
	public ModelAndView goMySttl() {
		ModelAndView mv = new ModelAndView("member/mypage/sttlList");
		return mv;
	}
}
