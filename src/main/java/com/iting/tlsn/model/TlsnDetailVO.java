package com.iting.tlsn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TlsnDetailVO {
	private String tlsnDetailNum; 	//수강 회차 번호
	private Double rndPrgre;		//회차 진도율
	private String ltNum;			//강의 번호
	private String tlsnNum;			//수강 번호
	private String rndNum;			//회차 번호 - 커리큘럼
}
