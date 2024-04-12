package com.iting.tlsn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.tlsn.model.TlsnVO;

@Mapper
public interface TlsnMapper {
	public List<TlsnVO> getTlsnList();
	public List<TlsnVO> getTlsnDetailList(String ltNum);
	public int tlsnInsert(TlsnVO vo); //수강신청(등록)
	public TlsnVO getTlsnInfo(TlsnVO vo); //수강 단건조회 
}
