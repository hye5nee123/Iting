package com.iting.cnq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.cnq.mapper.CnqMapper;
import com.iting.cnq.model.CnqVO;
import com.iting.cnq.service.CnqService;

@Service
public class CnqServiceImpl implements CnqService {
	@Autowired
	CnqMapper cnqMapper;

	@Override
	public List<CnqVO> cnqList(CnqVO vo) {
	
		return cnqMapper.cnqList(vo);
	}

}
