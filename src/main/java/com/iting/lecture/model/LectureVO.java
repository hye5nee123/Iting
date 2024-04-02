package com.iting.lecture.model;

import java.util.Date;

import lombok.Data;

@Data
public class LectureVO {
	private String ltNum;
	private String ltStCd;
	private String ltTtl;
	private String ltImg;
	private String ltIntro;
	private String ltCateCd;
	private Date frDt;
	private String accpYnCd;
	private String ltDifCd;
	private String lecturerNum;
	private String ltKeywordCd;
	private String rdmCateCd;
	private String ltFgCd;
	
}