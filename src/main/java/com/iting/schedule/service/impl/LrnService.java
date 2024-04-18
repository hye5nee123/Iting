package com.iting.schedule.service.impl;

import java.util.List;

import com.iting.schedule.model.LrnVO;

public interface LrnService {
	public int lrnInsert(LrnVO vo);
	public List<LrnVO> getLrnList();
}
