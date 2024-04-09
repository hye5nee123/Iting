package com.iting.common.model;

import lombok.Data;

@Data
public class AtchVO {
	private String atchNum; //번호
	private String atchPath; //경로
	private String atchTtl; //파일명
	private String ord; //순서
	private String atchTypCd; //유형 코드
	private String atchMarkTtl; //사이트에 표기할 파일명
}
