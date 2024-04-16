package com.iting.tlsn.model;

import java.util.Date;
import lombok.Data;

@Data
public class TlsnVO {
	private String tlsnNum;  //수강 번호
	private String tlsnStCd; //수강 상태 코드
	private String memNum;	 //회원 번호
	private String ltNum;	 //강의 번호
	private String ceteYnCd; //수료 여부 코드
	private Date applDt;	 //신청 날짜
	private String lnNum;
	private String ltTtl;
	private String ltCateCd;
	private String lecturerNum;
	private String id;
	private String addr;
	private String detailAddr;
	private String mail;
	private String phone;
	private String name;
	private String rndNum;
	private String rndTtl;
	private String ltTm;
	private String examYn;
	
	private String retCode;
	private String cnt;
}
