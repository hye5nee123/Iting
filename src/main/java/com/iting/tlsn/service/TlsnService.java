package com.iting.tlsn.service;

import java.util.List;

import com.iting.tlsn.model.TlsnVO;

public interface TlsnService {
	public List<TlsnVO> getTlsnList(String user);
	public List<TlsnVO> getTlsnDetailList(String ltNum, String user);
	public TlsnVO getTlsnInfo(TlsnVO vo);
}
