package com.iting.notice.model;

import java.util.Date;

import lombok.Data;
@Data
public class NoticeVO {
	private String noticeNum;
	private String ttl;
	private String cntn;
	private Date regDt;
	private String noticeTypCd;
	private Date fixFrDt;
	private Date fixToDt;
	private String priNoticeCd;
	private String atchNum;	
}
