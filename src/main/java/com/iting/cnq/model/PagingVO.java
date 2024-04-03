package com.iting.cnq.model;

import lombok.Data;

@Data
public class PagingVO {
	int pageUnit = 10; // 한페이지 출력할 레코드 건수
	int pageSize = 10; // 페이지번호 수 (5) 1~ 5까지
	long lastPage; // 마지막 페이지번호
	long totalRecord; // 전체 레코드건수
	Integer page = 1; // 현재 페이지
	long startPage; // 페이지그룹내에서 시작페이지번호
	long endPage; // 페이지그룹내에서 마지막페이지번호
	int first;
	int last;
}
