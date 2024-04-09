package com.iting.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.common.mapper.CommonMapper;
import com.iting.common.model.FileVO;
import com.iting.common.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonMapper commonMapper;

	@Override
	public int fileInsert(FileVO fvo) {
		return commonMapper.fileInsert(fvo);
	}

}
