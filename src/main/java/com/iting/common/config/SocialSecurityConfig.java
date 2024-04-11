package com.iting.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.iting.common.config.domain.user.Role;
import com.iting.common.service.CustomOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity
public class SocialSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.MEMBER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/main")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}