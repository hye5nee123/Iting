package com.iting.cnq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;

@Mapper
public interface CnqMapper {
	// 전체 list 조회.
	public List<CnqVO> getCnqList(CnqVO vo, CSearchVO svo);

	// 등록
	public int cnqInsert(CnqVO vo);

	// 상세조회
	public CnqVO getCnqInfo(String ltCnqNum);

	// 글에 대한 개수
	public long getCount(CnqVO vo, CSearchVO svo);

	// 수정
	public int updateCnq(CnqVO vo);
}
