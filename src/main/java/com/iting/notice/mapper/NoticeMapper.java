package com.iting.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.cnq.model.CSearchVO;
import com.iting.notice.model.NoticeVO;

@Mapper
public interface NoticeMapper {

	// 전체 list 조회.
	public List<NoticeVO> getNoticeList(NoticeVO vo, CSearchVO svo);

	// 글에 대한 개수
	public long getCount(NoticeVO vo, CSearchVO svo);
}
