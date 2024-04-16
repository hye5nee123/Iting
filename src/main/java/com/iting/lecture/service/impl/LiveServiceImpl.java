package com.iting.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.common.model.PagingVO;
import com.iting.lecture.mapper.LiveMapper;
import com.iting.lecture.model.LiveVO;
import com.iting.lecture.service.LiveService;

@Service
public class LiveServiceImpl implements LiveService{

	@Autowired
	LiveMapper liveMapper;

	@Override
	public List<LiveVO> getLiveList(LiveVO vo) {
		return liveMapper.getLiveList(vo);
	}

	@Override
	public List<LiveVO> ingLiveList(LiveVO vo) {
		return liveMapper.ingLiveList(vo);
	}

	@Override
	public List<LiveVO> endLiveList(LiveVO vo, PagingVO pvo) {
		return liveMapper.endLiveList(vo);
	}

	@Override
	public long getCount(LiveVO vo) {
		return liveMapper.getCount(vo, null);
	}

	@Override
	public List<LiveVO> getAllLiveList(LiveVO vo, PagingVO pvo) {
		return liveMapper.getAllLiveList(vo, pvo);
	}

	@Override
	public int update(LiveVO vo) {
		return liveMapper.update(vo);
	}

	@Override
	public int spltInsert(LiveVO vo) {
		return liveMapper.spltInsert(vo);
	}


	
}
