package com.iting.tlsn.model;

import lombok.Data;

@Data
public class TlsnDetailVO {
	private String tlsnRndNum; 	//수강 회차 번호
	private String rndTtl; 		//회차 제목
	private Double rndPrgre;	//회차 진도율
	private String ltNum;		//강의 번호
	private String tlsnNum;		//수강 번호
}
