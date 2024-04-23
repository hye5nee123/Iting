package com.iting.subsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.subsp.model.MySttlVO;
import com.iting.subsp.model.SttlVO;


@Mapper
public interface SttlMapper {
	int sttlInsert(SttlVO vo); // 결제등록
	List<MySttlVO> getSttlList(String memNum); // 마이페이지 결제내역
}
