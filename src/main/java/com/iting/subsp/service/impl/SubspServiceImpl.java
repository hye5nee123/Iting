package com.iting.subsp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.subsp.mapper.SubspMapper;
import com.iting.subsp.model.SubspVO;
import com.iting.subsp.service.SubspService;

@Service
public class SubspServiceImpl implements SubspService {

	@Autowired
	SubspMapper subspMapper;
	
	@Override
	public int subspInsert(SubspVO vo) {
		return subspMapper.subspInsert(vo);
	}

}
