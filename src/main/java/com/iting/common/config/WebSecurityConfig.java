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

import com.iting.common.security.CustomAccessDeniedHandler;
import com.iting.common.security.CustomAuthFailureHandler;
import com.iting.common.security.CustomLoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired UserDetailsService detailService;
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	@Bean
    AuthenticationFailureHandler loginFailureHandler(){
        return new CustomAuthFailureHandler();
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> requests
				.antMatchers("/*", "/member/**", "/js/**", "/css/**", "/img/**", "/common/**", "/admin/**", "/teacher/**").permitAll()
				.antMatchers("/admin/**").hasRole("B2")
				.anyRequest().authenticated())
//				.formLogin((form) -> form
//						.loginPage("/login")
//						.permitAll())
				.formLogin().loginPage("/login")
				.usernameParameter("userId")
//				.passwordParameter("password")
				.loginProcessingUrl("/userlogin")
				.successHandler(authenticationSuccessHandler())
//				.failureForwardUrl("/login")
				.failureHandler(loginFailureHandler())
				.permitAll()
				.and()
//				.logout((logout) -> logout.permitAll());
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/member/main")
				.permitAll()
				.and()
				//.exceptionHandling().accessDeniedHandler(AccessDeniedHandler());
				.exceptionHandling(handler -> handler
						.accessDeniedHandler(accessDeniedHandler()))
				//.csrf().disable()
				.userDetailsService(detailService)
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