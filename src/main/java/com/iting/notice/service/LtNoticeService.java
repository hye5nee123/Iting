package com.iting.notice.service;

import java.util.List;

import com.iting.notice.model.LtNoticeVO;

public interface LtNoticeService {
	//목록
	public List<LtNoticeVO> getLtNoticeList(LtNoticeVO vo);
	//등록
	public int ltNoticeInsert(LtNoticeVO vo);
}
