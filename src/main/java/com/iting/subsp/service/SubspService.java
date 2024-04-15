package com.iting.subsp.service;

import com.iting.subsp.model.SubspVO;

public interface SubspService {
	int subspInsert(SubspVO vo); // 구독등록
	SubspVO getSubspInfo(String memNum); // 구독 단건조회
	int subspUpdate(SubspVO vo); //구독 수정(빌링키 입력)
}
