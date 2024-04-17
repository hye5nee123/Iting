package com.iting.notice.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LtNoticeVO {
	private String ltNoticeNum;
	private String ltNoticeTtl;
	private String ltNoticeCntn;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date ltNoticeRegDt;
	private String ltNum;
}
