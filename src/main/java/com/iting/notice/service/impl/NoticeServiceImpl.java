package com.iting.notice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.notice.mapper.NoticeMapper;
import com.iting.notice.model.NoticeVO;
import com.iting.notice.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper noticeMapper;

	@Override
	public List<NoticeVO> getNoticeList(NoticeVO vo) {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeList(vo);
	}



}
