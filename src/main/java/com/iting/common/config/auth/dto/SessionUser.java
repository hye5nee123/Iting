package com.iting.common.config.auth.dto;

import lombok.Getter;

import java.io.Serializable;

import com.iting.common.config.domain.user.Mem;

@Getter
public class SessionUser implements Serializable {
	
	private String memNum;
    private String mail;
    private String phone;

    public SessionUser(Mem user) {
    	this.memNum = user.getMemNum();
        this.mail = user.getMail();
        this.phone = user.getPhone();
    }
}