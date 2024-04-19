package com.iting.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.schedule.mapper.LrnMapper;
import com.iting.schedule.model.LrnVO;

@Service
public class LrnServiceImpl implements LrnService {
	
	@Autowired
	LrnMapper lrnMapper;

	@Override
	public int lrnInsert(LrnVO vo) {
		return lrnMapper.lrnInsert(vo);
	}

	@Override
	public List<LrnVO> getLrnList() {
		return lrnMapper.getLrnList();
	}

}
