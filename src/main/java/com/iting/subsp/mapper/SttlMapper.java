package com.iting.subsp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.iting.subsp.model.SttlVO;

@Mapper
public interface SttlMapper {
	int sttlInsert(SttlVO vo); // 결제등록
}
