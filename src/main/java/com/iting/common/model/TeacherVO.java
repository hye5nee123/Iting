package com.iting.common.model;

import java.sql.Date;

import lombok.Data;

@Data
public class TeacherVO {
	private String lecturerNum;
	private String id;
	private String pw;
	private String name;
	private String addr;
	private String detailAddr;
	private String mail;
	private String phone;
	private String atchNum;
	private Date joinDt;
	private Date accpDt;
	private String accpYnCd;
	private String loginTypCd;
	private String token;
}
