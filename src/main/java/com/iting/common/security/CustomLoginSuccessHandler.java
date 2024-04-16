package com.iting.common.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.iting.lecture.service.LectureService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
	LectureService lectureService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		log.info("Login Success");
		List<String> roleNames = new ArrayList<>();
		//Collection<Authority> ==> List<String>
		auth.getAuthorities().forEach(authority -> {roleNames.add(authority.getAuthority());});
		
		CustomUsers user = (CustomUsers)auth.getPrincipal();
		String numcd = user.getUsersVO().getNumCd();
		String usercd = user.getUsersVO().getUserCd();
//		auth.getName(); // 단건조회 필요
		request.getSession().setAttribute("usertype", usercd);
		request.getSession().setAttribute("usernum", numcd);
		request.getSession().setAttribute("userId", auth.getName());
		
		request.getSession().setAttribute("myLectureList", lectureService.getTcList(numcd));
		
		if(roleNames.contains("ROLE_B2")) {
			response.sendRedirect("/admin/main");
		} else if(roleNames.contains("ROLE_D1")) {
			response.sendRedirect("/teacher/main");
		} else {
			response.sendRedirect("/member/main");
		}
	}

}
