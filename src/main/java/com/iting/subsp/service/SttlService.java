package com.iting.subsp.service;

import java.util.List;

import com.iting.subsp.model.MySttlVO;
import com.iting.subsp.model.SttlVO;

public interface SttlService {
	int sttlInsert(SttlVO vo); // 결제등록
	List<MySttlVO> getSttlList(String memNum); // 마이페이지 결제내역
}
