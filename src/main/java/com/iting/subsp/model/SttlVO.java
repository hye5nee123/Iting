package com.iting.subsp.model;

import java.sql.Date;

import lombok.Data;

@Data
public class SttlVO {
	private String sttlNum; 	// 결제 번호
	private String sttlTypCd;   // 결제 유형 코드
	private Date sttlDt; 		// 결제 날짜
	private String sttlStCd; 	// 결제 상태 코드
	private int dcPrice; 		// 할인 금액
	private int allSttlPrice; 	// 총 결제 금액
	private String subspNum; 	// 구독 번호
	private String memNum; 		// 회원 번호
	private String sttlAccpNum; // 결제 승인 번호
	private String sttlRnd;		// 결제 회차
	
}
