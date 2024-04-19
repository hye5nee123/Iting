package com.iting.sett.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.sett.model.SettVO;

@Mapper
public interface SettMapper {
	public List<SettVO> getSettList(); 	// 정산 전체 리스트
	public SettVO getSettCount();		// 정산 통계
}
