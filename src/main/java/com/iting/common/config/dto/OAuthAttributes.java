package com.iting.common.config.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import com.iting.common.config.domain.user.Role;
import com.iting.common.config.domain.user.User;

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

    public static OAuthAttributes of(String registrationId, Map<String, Object> attributes) {
            return ofNaver("id", attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .email((String) attributes.get("email"))
                .phone((String) attributes.get("mobail"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
        		
                .phone(phone)
                .role(Role.MEMBER)
                .build();
    }

}