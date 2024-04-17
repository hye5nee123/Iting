package com.iting.lecture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurriVO {

	private String rndNum;
	private String rndTtl;
	private String atchNum;
	private String rndCntn;
	private String ltTm;
	private String ltNum;
	
}
