package com.iting.lecture.service;

import java.util.List;

import com.iting.common.model.PagingVO;
import com.iting.lecture.model.LectureVO;

public interface LectureService {
	// 장효은
	public List<LectureVO> getAllLectureList(LectureVO vo, PagingVO pvo);
	
	public List<LectureVO> getLectureList(LectureVO vo);
	public LectureVO getLectureInfo(String ltNum);
	public LectureVO getLectureInfo1(String ltNum);
	
	// 신수지
	public List<LectureVO> getTcList(String ltNum);
	public List<LectureVO> getCurriList(LectureVO vo);
	public LectureVO getCurriAll(String ltNum);
	public LectureVO getTcInfo(String ltNum, String lecturerNum);

  
	// 도승민
	// 등록
	public int ltInsert(LectureVO vo);
	//수정(승인여부)
	public int update(LectureVO vo);
	//승인
	public List<LectureVO> endLectureList(LectureVO vo, PagingVO pvo);
	//미승인
	public List<LectureVO> ingLectureList(LectureVO vo);
	//미승인 상세
	public LectureVO ingInfo(String ltNum);
	//개수 
	public long getCount(LectureVO vo);

	

}

