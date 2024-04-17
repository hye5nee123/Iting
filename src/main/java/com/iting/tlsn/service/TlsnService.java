package com.iting.tlsn.service;

import java.util.List;

import com.iting.tlsn.model.TlsnDetailVO;
import com.iting.tlsn.model.TlsnVO;

public interface TlsnService {
	public List<TlsnVO> getTlsnList(String user);
	public List<TlsnVO> getTlsnDetailList(String ltNum, String user);
	public int tlsnInsert(TlsnVO vo); //수강신청(등록)
	public TlsnVO getTlsnInfo(TlsnVO vo); //수강 단건조회 - join
	public TlsnVO getTlsnInfoMem(TlsnVO vo); //수강 단건조회 - 조건 2가지
	
	// 수강상세
	public int tlsnDetailInsert(TlsnDetailVO vo);
	public int tlsnDetailUpdate(TlsnDetailVO vo);
	public TlsnDetailVO getTlsnDetailInfo(String rndNum);
	public List<TlsnDetailVO> getTlsnDtCurriInfo(String rndNum);
}
