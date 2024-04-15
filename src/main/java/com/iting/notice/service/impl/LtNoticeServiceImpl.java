package com.iting.notice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.notice.mapper.LtNoticeMapper;
import com.iting.notice.model.LtNoticeVO;
import com.iting.notice.service.LtNoticeService;

@Service
public class LtNoticeServiceImpl implements LtNoticeService{
	@Autowired
	LtNoticeMapper ltnoticeMapper;
//목록리스트
	@Override
	public List<LtNoticeVO> getLtNoticeList(LtNoticeVO vo) {
		return ltnoticeMapper.getLtNoticeList(vo);
	}
	//등록
	@Override
	public int ltNoticeInsert(LtNoticeVO vo) {
		return ltnoticeMapper.ltNoticeInsert(vo);
	}
	//상세
	@Override
	public LtNoticeVO getLtNoticeInfo(String ltNoticeNum) {
		return ltnoticeMapper.getLtNoticeInfo(ltNoticeNum);
	}



}
