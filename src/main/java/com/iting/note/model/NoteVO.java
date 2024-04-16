package com.iting.note.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoteVO {
	private String noteNum;
	private String noteTtl;
	private String noteCntn;
	private String rply;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sentDt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recDt;
	private String cfmYnCd;
	private String sentPs;
	private String recPs;
	private String id;
	private String name;
	private String ltNum;
	private String user;
}
