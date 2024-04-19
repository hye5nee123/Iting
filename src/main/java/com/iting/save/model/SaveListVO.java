package com.iting.save.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SaveListVO {
	private String ltStCd;
	private String ltTtl;
	private String ltNum;
	private String ltImg;
	private String ltIntro;
	private String ltCateCd;
	private Date frDt;
	private String lecturerName;
	private String ltKeywordCd;
}
