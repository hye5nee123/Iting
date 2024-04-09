package com.iting.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.member.model.MemberVO;
import com.iting.member.service.MemberService;
import com.iting.tlsn.service.TlsnService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	TlsnService tlsnService;
	
	// 마이페이지
	@RequestMapping("member/mycalss/list")
	public String getMemberList(Model model, MemberVO vo) {
		model.addAttribute("testList", memberService.getMemberList());
		model.addAttribute("tlsnList", tlsnService.getTlsnList());
		return "member/myclass/list";
	}
}
