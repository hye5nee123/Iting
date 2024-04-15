package com.iting.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.lecture.model.LiveVO;

@Mapper
public interface LiveMapper {
	//라이브 목록
	public List<LiveVO> getLiveList(LiveVO vo);
}
