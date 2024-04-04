package com.iting.subsp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.iting.subsp.model.SubspVO;

@Mapper
public interface SubspMapper {
	int subspInsert(SubspVO vo); // 구독등록
	SubspVO getSubspInfo(String memNum); // 구독 단건조회
}
