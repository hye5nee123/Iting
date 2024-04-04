package com.iting.common.web;


import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iting.common.ExcelView;
import com.iting.common.service.UsersService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CommonController {
	
	@Autowired
	UsersService userservice;
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
	
	
	/* 엑셀로 DB 테이블 내려받기 */
	@GetMapping("/empExcel")
	public ModelAndView empExcel() {
		ModelAndView mv = new ModelAndView(new ExcelView());
		/*
		mv.addObject("type", EmpVO.class);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map> list = empService.getEmpList(null)
                                   .stream()
                                   .map(d->objectMapper.convertValue(d, Map.class))
                                   .collect(Collectors.toList());
	
		mv.addObject("datas", list);
		*/
		return mv;
	}
	
	
	
	/* 로그인 및 로그아웃 */
	
	//로그인 페이지 이동
	@GetMapping("/login")
	public String loginForm() {
		return "common/login";
	}
	
//	@GetMapping("/loginfail")
//	public void loginfail(Model model)	{
//		model.addAttribute("failmessage", "아이디 혹은 비밀번호가 다릅니다");
//	}
	
	//로그인 정보 체크
//	@ResponseBody
//	@GetMapping("/common/chkuser")
//	public UsersVO dataChk(@RequestBody String id, Model model) {
//		System.out.println(id);
//		UsersVO vo = userservice.getUserInfo(id);
//		return vo;
//	}
	
	//로그인 정보 가져오기
//	@RequestMapping("/userlogin")
//	public void sessionLogin(String userid, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		UsersVO getinfo = userservice.getUserInfo(userid);
//		if(getinfo != null) {
//			session.setAttribute("userid", getinfo);
//		} else {
//			session.setAttribute("userid", null);
//		}
//	}
	
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
	
//	@ResponseBody
//	@PostMapping("/logout")
//	public String logout(HttpSession session) {
//		log.info("logout success");
//		System.out.println(session);
//		System.out.println(session.getAttribute("userId"));
//		System.out.println("로그아웃 성공");
////		session.invalidate(); // 세션 비워줌
//		return "redirect:/login";
//	}
}
