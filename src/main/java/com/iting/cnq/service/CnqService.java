package com.iting.cnq.service;

import java.util.List;
import java.util.Map;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;

public interface CnqService {
	public Map<String, Object> getCnqList(CnqVO vo, CSearchVO svo);

	public int cnqInsert(CnqVO vo);

	public CnqVO getCnqInfo(String ltCnqNum);

	public long getCount(CnqVO vo, CSearchVO svo);
}
