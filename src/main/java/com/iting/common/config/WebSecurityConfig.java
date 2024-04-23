package com.iting.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.iting.common.config.auth.CustomOAuth2UserService;
import com.iting.common.security.CustomAccessDeniedHandler;
import com.iting.common.security.CustomAuthFailureHandler;
import com.iting.common.security.CustomLoginSuccessHandler;
import com.iting.common.security.CustomLogoutSuccessHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired UserDetailsService detailService;
	
	private final CustomOAuth2UserService customOAuth2UserService;
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	@Bean
    public AuthenticationFailureHandler loginFailureHandler(){
        return new CustomAuthFailureHandler();
    }
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> requests
				.antMatchers("/*"
							, "/member/**"
							, "/js/**"
							, "/css/**"
							, "/img/**"
							, "/video/**"
							, "/common/**"
							, "/lecture/**"
							, "/download/**"
							, "/upload/**"
							, "/js/*"
							, "/downloading/**"
							).permitAll() // 전체 권한 허용
				.antMatchers("/admin/**").hasRole("B2") // 관리자 권한 허용
				.antMatchers("/teacher/**").hasRole("D1") // 강사 권한 허용
				.anyRequest().authenticated())
				.formLogin().loginPage("/commonlogin") // 로그인 폼 지정
				.usernameParameter("userId") // 아이디 폼 이름
				.loginProcessingUrl("/userlogin") // 로그인 작동 url
				.successHandler(authenticationSuccessHandler()) // 로그인 성공 핸들러
//				.failureForwardUrl("/commonlogin")
//				.failureUrl("/commonlogin")
//				.failureHandler(loginFailureHandler()) // 로그인 실패 핸들러
				.permitAll() // 요청 허용
				.and()
				.logout()
				.logoutUrl("/logout") // 로그아웃 요청 주소
				.logoutSuccessHandler(logoutSuccessHandler()) // 로그아웃 성공 핸들러
				.invalidateHttpSession(true).deleteCookies("JSESSIONID") // 세션 및 쿠키 제거
				.permitAll()
				.and()
				.headers()
				.frameOptions()
				.sameOrigin()
				.and()
				.exceptionHandling(handler -> handler
						.accessDeniedHandler(accessDeniedHandler())) // 접근 거부 핸들러
				.userDetailsService(detailService) 
				;
		http.oauth2Login()
		.userInfoEndpoint()
		.userService(customOAuth2UserService)
		;
		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("1111").roles("USER").build();
//		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}
}