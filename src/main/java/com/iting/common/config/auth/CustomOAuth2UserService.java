package com.iting.common.config.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.iting.common.config.auth.dto.OAuthAttributes;
import com.iting.common.config.auth.dto.SessionUser;
import com.iting.common.config.domain.user.Mem;
import com.iting.common.config.domain.user.UserRepository;
import com.iting.common.mapper.UsersMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	
	private final UserRepository userRepository;
	private final UsersMapper mapper;
	private final HttpSession httpSession;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //로그인 서비스 구분
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName(); //로그인 진행시 키가되는 필드값

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes()); //OAuth2User의 attribute를 담음

        Mem user = saveOrUpdate(attributes);
        httpSession.setAttribute("userId", new SessionUser(user).getMail());
        httpSession.setAttribute("usernum", new SessionUser(user).getMemNum());
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    private Mem saveOrUpdate(OAuthAttributes attributes) {
    	String memnum = mapper.getUserNum();
    	
    	Mem user = userRepository.findByMail(attributes.getMail())
                .map(entity -> entity.update(attributes.getPhone()))
                .orElse(attributes.toEntity(memnum));

        return userRepository.save(user);
    }
}
