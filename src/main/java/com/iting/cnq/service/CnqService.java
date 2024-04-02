package com.iting.cnq.service;

import java.util.List;

import com.iting.cnq.model.CnqVO;

public interface CnqService {
	public List<CnqVO> cnqList(CnqVO vo);
	public int cnqInsert(CnqVO vo);
	public CnqVO getCnqInfo(String ltCnqNum);
}
