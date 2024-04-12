package com.iting.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.notice.model.LtNoticeVO;

@Mapper
public interface LtNoticeMapper {
	//강의공지 리스트
	public List<LtNoticeVO> getLtNoticeList(LtNoticeVO vo);
	//강의공지 등록
	public int ltNoticeInsert(LtNoticeVO VO);
	//강의 공지 상세
	public LtNoticeVO getLtNoticeInfo(String ltNoticeNum);
}
