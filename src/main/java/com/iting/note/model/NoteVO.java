package com.iting.note.model;

import java.util.Date;
import lombok.Data;

@Data
public class NoteVO {
	private String noteNum;
	private String noteTtl;
	private String noteCntn;
	private String rply;
	private Date sentDt;
	private Date recDt;
	private String cfmYnCd;
	private String sentPs;
	private String recPs;
}
