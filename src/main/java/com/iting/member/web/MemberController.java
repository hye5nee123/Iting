package com.iting.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.member.model.MemberVO;
import com.iting.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	// 마이페이지
		@RequestMapping("member/mycalss/list")
		public String getMemberList(Model model, MemberVO vo) {
			model.addAttribute("testList", memberService.getMemberList());
			return "member/myclass/list";
		}
}
