package com.iting.common.web;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iting.lecture.model.LectureVO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CommonController {
	/* 메인이동 */
	// 관리자
	@RequestMapping("admin/main")
	public ModelAndView goAdminMain() {
		ModelAndView mv  = new ModelAndView("admin/main");
		return mv;
	}
	
	// 회원
	@RequestMapping("member/main")
	public ModelAndView goMemberMain() {
		ModelAndView mv  = new ModelAndView("member/main");
		return mv;
	}
	
	// 강사
	@RequestMapping("teacher/main")
	public ModelAndView goTeacherMain() {
		ModelAndView mv  = new ModelAndView("teacher/main");
		return mv;
	}
	
	
	
	/* 로그인 및 로그아웃 */
	@GetMapping("/login")
	public String loginForm() {
		return "common/login";
	}
	
	@GetMapping("/account")
	public String accountForm() {
		return "common/account";
	}
	
	@GetMapping("/accessError")
	public String accessDenied(Authentication auth, Model model) {
		log.info("access denied :" + auth);
		model.addAttribute("msg", "access denied");
		return "common/accessError";
	}
	
	@GetMapping("/logout")
	public String logout() {
		log.info("logout success");
		return "member/main";
	}
	
	
}
