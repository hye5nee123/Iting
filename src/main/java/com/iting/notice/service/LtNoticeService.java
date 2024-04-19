package com.iting.notice.service;

import java.util.List;

import com.iting.notice.model.LtNoticeVO;

public interface LtNoticeService {
	//목록
	public List<LtNoticeVO> getLtNoticeList(LtNoticeVO vo);
	//등록
	public int ltNoticeInsert(LtNoticeVO vo);
	//상세
	public LtNoticeVO getLtNoticeInfo(String ltNoticeNum);
	//보기용 목록
	public List<LtNoticeVO> getMltNoticeList(LtNoticeVO vo);
	//보기용 상세
	public LtNoticeVO getMltNoticeInfo(String ltNoticeNum);
}
