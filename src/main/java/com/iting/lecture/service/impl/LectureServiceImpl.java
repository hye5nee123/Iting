package com.iting.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.common.model.PagingVO;
import com.iting.lecture.mapper.LectureMapper;
import com.iting.lecture.model.LectureVO;
import com.iting.lecture.service.LectureService;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	LectureMapper lectureMapper;
	
	@Override
	public List<LectureVO> getAllLectureList(LectureVO vo, PagingVO pvo) {
		return lectureMapper.getAllLectureList(vo, pvo);
	}

	//도승민
	//조회
	@Override
	public List<LectureVO> getLectureList(LectureVO vo) {
		return lectureMapper.getLectureList(vo);
	}
	//상세
	@Override
	public LectureVO getLectureInfo(String ltNum) {
		return lectureMapper.getLectureInfo(ltNum);
	}

	@Override
	public LectureVO getLectureInfo1(String ltNum) {
		return lectureMapper.getLectureInfo1(ltNum);
	}

	// 신수지
	@Override
	public List<LectureVO> getTcList(String lecturerNum) {
		return lectureMapper.getTcList(lecturerNum);
	}
	
	@Override
	public List<LectureVO> getCurriList(LectureVO vo) {
		return lectureMapper.getCurriList(vo);
	}
	
	@Override
	public LectureVO getCurriAll(String ltNum) {
		return lectureMapper.getCurriAll(ltNum);
	}
	
	@Override
	public LectureVO getTcInfo(String ltNum, String lecturerNum) {
		return lectureMapper.getTcInfo(ltNum, lecturerNum);
	}
  
	// 도승민
	@Override
	public int ltInsert(LectureVO vo) {
		return lectureMapper.ltInsert(vo);
	}

	@Override
	public int update(LectureVO vo) {
		return lectureMapper.update(vo);
	}
	
	
	@Override
	public List<LectureVO> ingLectureList(LectureVO vo) {
		return lectureMapper.ingLectureList(vo);
	}
	
	
	
	@Override
	public List<LectureVO> endLectureList(LectureVO vo, PagingVO pvo) {
		return lectureMapper.getAllLectureList(vo, pvo);
	}
	
	@Override
	public long getCount(LectureVO vo) {
		return lectureMapper.getCount(vo, null);
	}
	//미승인 상세
	@Override
	public LectureVO ingInfo(String ltNum) {
		return lectureMapper.ingInfo(ltNum);
	}



}
