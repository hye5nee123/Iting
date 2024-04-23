package com.iting.notice.service;

import java.util.List;

import com.iting.notice.model.NoticeVO;

public interface NoticeService {
	// 전체 list 조회.
	public List<NoticeVO> getNoticeList(NoticeVO vo);
	
}
