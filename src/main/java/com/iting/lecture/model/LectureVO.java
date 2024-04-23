package com.iting.lecture.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LectureVO {
	private String ltNum;		//강의 번호
	private String ltStCd;		//강의 상태 코드
	private String ltStNm;		//강의 상태 코드명
	private String ltTtl;		//강의 제목
	private String ltImg;		//강의 이미지
	private String ltIntro;		//강의 소개
	private String ltCateCd;	//강의 카테고리 코드
	private String ltCateNm;	//강의 카테고리 코드명
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date frDt;			//시작 날짜
	private String accpYnCd;	//승인 여부 코드
	private String accpYnNm;	//승인 여부 코드명
	private String ltDifCd; 	//강의 난이도 코드
	private String ltDifNm; 	//강의 난이도 코드명
	private String lecturerNum; //강사 번호
	private String ltKeywordCd; //강의 키워드 코드
	private String ltKeywordNm; //강의 키워드 코드명
	private String rdmCateCd;	//로드맵 카테고리 코드
	private String rdmCateNm;	//로드맵 카테고리 코드명
	
	
	private String name;		// 강사명
	private String id; 			// 강사 아이디
	private String addr; 		// 강사 주소
	private String detailAddr; 	// 강사 주소
	private String mail; 		// 강사 메일
	private String phone; 		// 강사 연락처
	
	private String rndNum;
	private String rndTtl;
	private String atchNum;
	private String ltTm;
	private String cnt;
	private String sum;
	
	//페이징
	int start = 1;
	int end = 10;
	
	private int reviewAvg;
	
}