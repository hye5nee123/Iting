package com.iting.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class AccountVO {
	private String id;
	private String pw;
	private String name;
	private String addr;
	private String detailAddr;
	private String mail;
	private String phone;
	private Date joinDt;
	private String loginTypCd;
	private String token;
	
	private String accNum;
	private String accCd;
}
