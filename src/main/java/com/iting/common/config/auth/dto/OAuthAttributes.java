package com.iting.common.config.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.iting.common.config.domain.user.Mem;
import com.iting.common.config.domain.user.Role;
import com.iting.common.config.domain.user.UserRepository;

@Getter
public class OAuthAttributes {
	
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String mail;
    private String phone;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email, String phone) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.mail = email;
        this.phone = phone;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }
    
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
        		.email((String) attributes.get("email"))
                .phone((String) attributes.get("mobile"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
    	 Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .email((String) response.get("email"))
                .phone((String) response.get("mobile"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Mem toEntity() {
        return Mem.builder()
        		.mail(mail)
                .phone(phone)
                .role(Role.b1)
                .build();
    }

}