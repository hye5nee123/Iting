package com.iting.review.model;

import java.util.Date;

import lombok.Data;
@Data
public class ReviewVO {
	private String reviewNum;
	private String ttl;
	private String cntn;
	private Date drwupDt;
	private Integer star;
	private String memNum;
	private String tlsnNum;
	private String ltNum;
	private String name;
}
