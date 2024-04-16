package com.iting.subsp.model;

import java.util.Date;

import lombok.Data;

@Data
public class MySttlVO {
	private String subspTypCd;
	private Date subspFrDt;
	private Date subspToDt;
	private String sttlTypCd;
	private Date sttlDt;
	private int dcPrice;
	private int allSttlPrice;
	private int sttlRnd;
}
