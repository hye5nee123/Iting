package com.iting.tlsn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.tlsn.mapper.TlsnMapper;
import com.iting.tlsn.model.TlsnVO;
import com.iting.tlsn.service.TlsnService;

@Service
public class TlsnServiceImpl implements TlsnService {

	@Autowired
	TlsnMapper tlsnMapper;
	
	@Override
	public List<TlsnVO> getTlsnList() {
		return tlsnMapper.getTlsnList();
	}

	@Override
	public List<TlsnVO> getTlsnDetailList() {
		return tlsnMapper.getTlsnDetailList();
	}
	
}