package com.iting.cnq.service;

import java.util.Map;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;

public interface ReplyService {

	// 목록 출력(그냥).
	public Map<String, Object> getList(CnqVO vo, CSearchVO svo);

	//등록(ajax)
	public int cmtInsert(CnqVO vo);
	
	
	//개수
	public long getCount(CnqVO vo);
}
