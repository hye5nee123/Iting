package com.example.iting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.iting.security.CustomAccessDeniedHandler;
import com.example.iting.security.CustomLoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired 
	UserDetailsService detailService; 
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/*", "/home").permitAll()
				// .antMatchers("/admin/*")
				//.antMatchers("/empList").hasRole("ADMIN")
				//.anyRequest().authenticated()
			)
			// 람다식 표현
//			.formLogin((form) -> form
//				.loginPage("/login")
//				.permitAll()
//			)
			
			.formLogin().loginPage("/login")
						.usernameParameter("userid")
						.loginProcessingUrl("/userlogin")
						.successHandler(successHandler())
						.permitAll()
			.and()
			// .logout((logout) -> logout.permitAll())
			.logout()
					.logoutSuccessUrl("/customLogout")
					.permitAll()
			.and()
			// .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
			.exceptionHandling( handler -> handler
					.accessDeniedHandler(accessDeniedHandler()) )
					// .csrf().disable() // token 발행 안하고 안넘어감
			.userDetailsService(detailService)
			;
		return http.build();
	}

	/* @Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("1111")
				.roles("USER") // ROLE_USER ==> USER
				.build();

		
		UserDetails admin =
				User.withDefaultPasswordEncoder()
				.username("admin")
				.password("1111")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}*/ 
}