package com.iting.lecture.service;

import java.util.List;

import com.iting.lecture.model.LectureVO;

public interface LectureService {
	public List<LectureVO> getLectureList(LectureVO vo);
	public LectureVO getLectureInfo(String ltNum);
	public LectureVO getLectureInfo1(String ltNum);
  // 신수지
	public List<LectureVO> getTcList(String lecturerNum);

  
	// 도승민
	//등록
	public int ltInsert(LectureVO vo);
	//수정(승인여부)
	public int update(LectureVO vo);
	//승인
	public List<LectureVO> endLectureList(LectureVO vo);
	//미승인
	public List<LectureVO> ingLectureList(LectureVO vo);
}

