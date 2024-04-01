package com.iting.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iting.member.model.MemberVO;

@Controller
public class MemberController {

	@RequestMapping("member/main")
	public ModelAndView getTestList(MemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("member/main");
		return mv;
	}
}
