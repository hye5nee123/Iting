package com.iting.notice.service;

import java.util.Map;

import com.iting.cnq.model.CSearchVO;
import com.iting.notice.model.NoticeVO;

public interface NoticeService {
	// 전체 list 조회.
	public Map<String,Object> getNoticeList(NoticeVO vo, CSearchVO svo);
	
}
