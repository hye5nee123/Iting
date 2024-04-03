package com.iting.lecture.service;

import java.util.List;

import com.iting.lecture.model.LectureVO;

public interface LectureService {
	public List<LectureVO> getLectureList(LectureVO vo);
	public LectureVO getLectureInfo(String ltNum);
	public LectureVO getLectureInfo1(String ltNum);
}
