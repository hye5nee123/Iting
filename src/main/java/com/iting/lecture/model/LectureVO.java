package com.iting.lecture.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class LectureVO {
	private String ltNum;
	private String ltStCd;
	private String ltTtl;
	private String ltImg;
	private String ltIntro;
	private String ltCateCd;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date frDt;
	private String accpYnCd;
	private String ltDifCd;
	private String lecturerNum;
	private String ltKeywordCd;
	private String rdmCateCd;
	private String ltFgCd;
	
}