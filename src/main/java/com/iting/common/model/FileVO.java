package com.iting.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {			// ATCH (첨부파일)테이블
	private String atchNum;		//첨부파일 번호
	private String atchPath;	//첨부파일 경로
	private String atchTtl; 	//첨부파일 제목 (원본파일명)
	private int ord;			//순서
	private String atchTypCd;	//첨부파일 유형 코드(k1 이미지 / k2 동영상 / k3 문서)
	private String atchMarkTtl;	//첨부파일 표기 제목 (새파일명)
}
