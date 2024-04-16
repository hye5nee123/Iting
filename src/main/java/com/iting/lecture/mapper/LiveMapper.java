package com.iting.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.common.model.PagingVO;
import com.iting.lecture.model.LiveVO;

@Mapper
public interface LiveMapper {
	//라이브 목록
	public List<LiveVO> getLiveList(LiveVO vo);
	//미승인
	public List<LiveVO> ingLiveList(LiveVO vo);
	//승인
	public List<LiveVO> endLiveList(LiveVO vo);
	//갯수
	public long getCount(LiveVO vo, PagingVO pvo);
	
	public List<LiveVO> getAllLiveList(LiveVO vo, PagingVO pvo);
	//승인수정
	public int update(LiveVO vo);
	//등록
	public int spltInsert(LiveVO vo);
	
}
