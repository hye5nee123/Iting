package com.iting.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.lecture.model.CurriVO;

@Mapper
public interface CurriMapper {
	
	//목록 리스트
	public List<CurriVO> getCurriList(CurriVO vo);
	//상세
	public CurriVO getCurriInfo(String rndNum);
	//등록
	public int CurriInsert(CurriVO vo);
}
