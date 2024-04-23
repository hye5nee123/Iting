package com.iting.lecture.service;

import java.util.List;

import com.iting.common.model.PagingVO;
import com.iting.lecture.model.LiveVO;

public interface LiveService {
	//목록
	public List<LiveVO> getLiveList(LiveVO vo);
	//미승인
	public List<LiveVO> ingLiveList(LiveVO vo);
	//승인
	public List<LiveVO> endLiveList(LiveVO vo, PagingVO pvo);
	//갯수
	public long getCount(LiveVO vo);
	//전체 목록
	public List<LiveVO> getAllLiveList(LiveVO vo, PagingVO pvo);
	//수정
	public int update(LiveVO vo);
	//등록
	public int spltInsert(LiveVO vo);
	//상세
	public LiveVO getLiveInfo(String spltNum);
}
