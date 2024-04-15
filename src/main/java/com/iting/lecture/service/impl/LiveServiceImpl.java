package com.iting.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
