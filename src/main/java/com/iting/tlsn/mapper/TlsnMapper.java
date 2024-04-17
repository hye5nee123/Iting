package com.iting.tlsn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.tlsn.model.TlsnDetailVO;
import com.iting.tlsn.model.TlsnVO;

@Mapper
public interface TlsnMapper {
	public List<TlsnVO> getTlsnList(String user);
	public List<TlsnVO> getTlsnDetailList(String ltNum, String user);
	public int tlsnInsert(TlsnVO vo); //수강신청(등록)
	public TlsnVO getTlsnInfo(TlsnVO vo); //수강 단건조회 - join
	public TlsnVO getTlsnInfoMem(TlsnVO vo); //수강 단건조회 - 조건 2가지
	
	// 수강상세
	public int tlsnDetailInsert(TlsnDetailVO vo);
}
