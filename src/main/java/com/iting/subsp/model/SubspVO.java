package com.iting.subsp.model;

import java.sql.Date;

import lombok.Data;

@Data
public class SubspVO {
	private String subspNum;
	private String subspStCd;
	private Date subspFrDt;
	private Date subspToDt;
	private String subspTypCd;
	private int subspPrice;
	private String memNum;
	
	private String billingKey;
}
