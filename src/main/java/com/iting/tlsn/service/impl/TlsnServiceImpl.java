package com.iting.tlsn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.tlsn.mapper.TlsnMapper;
import com.iting.tlsn.model.TlsnDetailVO;
import com.iting.tlsn.model.TlsnVO;
import com.iting.tlsn.service.TlsnService;

@Service
public class TlsnServiceImpl implements TlsnService {

	@Autowired
	TlsnMapper tlsnMapper;
	
	@Override
	public List<TlsnVO> getTlsnList(String user) {
		return tlsnMapper.getTlsnList(user);
	}

	@Override
	public List<TlsnVO> getTlsnDetailList(String ltNum, String user) {
		return tlsnMapper.getTlsnDetailList(ltNum, user);
	}

	@Override
	public TlsnVO getTlsnInfo(TlsnVO vo) {
		return tlsnMapper.getTlsnInfo(vo);
	}

  @Override
	public int tlsnInsert(TlsnVO vo) {
		return tlsnMapper.tlsnInsert(vo);
	}

@Override
public TlsnVO getTlsnInfoMem(TlsnVO vo) {
	return tlsnMapper.getTlsnInfoMem(vo);
}

@Override
public int tlsnDetailInsert(TlsnDetailVO vo) {
	return tlsnMapper.tlsnDetailInsert(vo);
}
	
}
