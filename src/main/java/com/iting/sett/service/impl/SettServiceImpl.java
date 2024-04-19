package com.iting.sett.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.sett.mapper.SettMapper;
import com.iting.sett.model.SettVO;
import com.iting.sett.service.SettService;

@Service
public class SettServiceImpl implements SettService {
	
	@Autowired
	SettMapper settMapper;

	@Override
	public List<SettVO> getSettList() {
		return settMapper.getSettList();
	}

	@Override
	public SettVO getSettCount() {
		return settMapper.getSettCount();
	}
	
}
