package com.iting.lecture.service;

import java.util.List;

import com.iting.lecture.model.CurriVO;

public interface CurriService {

	//목록
	public List<CurriVO> getCurriList(CurriVO vo);
	//상세
	public CurriVO getCurriInfo(String rndNum);
	//등록
	public int curriInsert(CurriVO vo);
}
