package com.iting.sett.service;

import java.util.List;

import com.iting.sett.model.SettVO;

public interface SettService {
	public List<SettVO> getSettList(); 	// 정산 전체 리스트
	public SettVO getSettCount();		// 정산 통계
}
