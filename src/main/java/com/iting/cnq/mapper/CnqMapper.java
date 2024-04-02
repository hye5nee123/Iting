package com.iting.cnq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.cnq.model.CnqVO;
import com.iting.cnq.model.CSearchVO;

@Mapper
public interface CnqMapper {
	public List<CnqVO> getCnqList(CnqVO vo, CSearchVO svo);

	public int cnqInsert(CnqVO vo);

	public CnqVO getCnqInfo(String ltCnqNum);

	public long getCount(CnqVO vo, CSearchVO svo);
}
