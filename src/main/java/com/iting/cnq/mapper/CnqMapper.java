package com.iting.cnq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.cnq.model.CnqVO;

@Mapper
public interface CnqMapper {
	public List<CnqVO> cnqList(CnqVO vo);
	public int cnqInsert(CnqVO vo);
	//제발
}
