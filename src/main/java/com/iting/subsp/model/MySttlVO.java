package com.iting.subsp.model;


import lombok.Data;

@Data
public class MySttlVO {
	private String subspTypCd;
	private String subspFrDt;
	private String subspToDt;
	private String sttlTypCd;
	private String sttlDt;
	private int dcPrice;
	private int allSttlPrice;
	private int sttlRnd;
}
