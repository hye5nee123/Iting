package com.iting.lecture.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LiveVO {
	private String spltNum; //특강 번호
	private String spltTtl; //특강 제목
	private String spltCntn; // 특강 내용
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date spltDt; //특강 날짜
	private String lecturerNum; //강사 번호
	private String accpYnCd; //승인 여부
	
}
