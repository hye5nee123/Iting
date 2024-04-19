package com.iting.sett.model;

import java.util.Date;

import lombok.Data;

@Data
public class SettVO {
	private String settNum;		//정산 번호
	private int salary;			//기본급
	private int incn;			//인센티브
	private int allSettPrice;	//총 정산 금액
	private String settStCd;	//정산 상태 코드
	private Date settDt;		//정산 날짜
	private String lecturerNum; //강사 번호
	private String ltNum;		//강의 번호
	
	private int settIng;		//정산중 수
	private int settFin;		//정산완료 수
	
}
