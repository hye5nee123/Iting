package com.iting.member.model;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVO {
	private String memNum;
	private String id;
	private String pw;
	private String name;
	private String addr;
	private String detailAddr;
	private String mail;
	private String phone;
	private String intCd;
	private Date joinDt;
	private String memFgCd;
	private String loginTypCd;
	private String token;
}
