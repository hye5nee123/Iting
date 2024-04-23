package com.iting.subsp.model;

import java.sql.Date;

import lombok.Data;

@Data
public class SubspVO {
	private String subspNum;
	private String subspStCd;
	private String subspFrDt;
	private String subspToDt;
	private String subspTypCd;
	private int subspPrice;
	private String memNum;
	private String billingKey; 	// 빌링키
	private int subremain; 	// 남은 기간
	
	
	private String retCode;
}
