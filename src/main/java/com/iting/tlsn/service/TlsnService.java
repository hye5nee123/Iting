package com.iting.tlsn.service;

import java.util.List;

import com.iting.tlsn.model.TlsnVO;

public interface TlsnService {
	public List<TlsnVO> getTlsnList(String user);
	public List<TlsnVO> getTlsnDetailList(String ltNum, String user);
	public int tlsnInsert(TlsnVO vo); //수강신청(등록)
	public TlsnVO getTlsnInfo(TlsnVO vo); //수강 단건조회 - join
	public TlsnVO getTlsnInfoMem(TlsnVO vo); //수강 단건조회 - 조건 2가지
}
