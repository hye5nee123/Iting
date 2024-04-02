package com.iting.common.web;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CommonController {
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
