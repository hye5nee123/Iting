package com.iting.cnq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;

@Mapper
public interface CmtMapper {
	// 목록 출력(그냥).
	public List<CnqVO> getListWithPaging(CnqVO vo, CSearchVO svo);

	// 개수 계산.
	public long getCount(CnqVO vo);

	//등록(ajax)
	public int cmtInsert(CnqVO vo);
}
