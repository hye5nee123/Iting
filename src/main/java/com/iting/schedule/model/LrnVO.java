package com.iting.schedule.model;

import java.util.Date;

import lombok.Data;

@Data
public class LrnVO {
	private String lrnScheNum;
	private String scheCntn;
	private String memNum;
	private Date frDt;
	private Date toDt;
}
