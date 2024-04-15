package com.iting.notice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.cnq.model.CSearchVO;
import com.iting.notice.mapper.NoticeMapper;
import com.iting.notice.model.NoticeVO;
import com.iting.notice.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper noticeMapper;

	@Override
	public Map<String, Object> getNoticeList(NoticeVO vo, CSearchVO svo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", noticeMapper.getNoticeList(vo, svo));
		map.put("count", noticeMapper.getCount(vo, svo));
		return map;
	}

}
