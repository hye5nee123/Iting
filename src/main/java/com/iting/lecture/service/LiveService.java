package com.iting.lecture.service;

import java.util.List;

import com.iting.lecture.model.LiveVO;

public interface LiveService {
	public List<LiveVO> getLiveList(LiveVO vo);
}
