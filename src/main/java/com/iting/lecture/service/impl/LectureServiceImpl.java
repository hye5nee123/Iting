package com.iting.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.lecture.mapper.LectureMapper;
import com.iting.lecture.model.LectureVO;
import com.iting.lecture.service.LectureService;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	LectureMapper lectureMapper;

	@Override
	public List<LectureVO> getLectureList(LectureVO vo) {
		return lectureMapper.getLectureList(vo);
	}

	@Override
	public LectureVO getLectureInfo(String ltNum) {
		return lectureMapper.getLectureInfo(ltNum);
	}

	@Override
	public LectureVO getLectureInfo1(String ltNum) {
		return lectureMapper.getLectureInfo1(ltNum);
	}


}
