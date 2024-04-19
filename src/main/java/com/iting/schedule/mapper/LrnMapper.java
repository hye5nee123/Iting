package com.iting.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.schedule.model.LrnVO;

@Mapper
public interface LrnMapper {
	public int lrnInsert(LrnVO vo);
	public List<LrnVO> getLrnList();
}
