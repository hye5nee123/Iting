package com.iting.common.config.dto;

import lombok.Getter;

import java.io.Serializable;

import com.iting.common.config.domain.user.User;

@Getter
public class SessionUser implements Serializable {

    private String mail;
    private String phone;

    public SessionUser(User user) {
        this.mail = user.getMail();
        this.phone = user.getPhone();
    }
}