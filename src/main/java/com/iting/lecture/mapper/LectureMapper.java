package com.iting.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.common.model.PagingVO;
import com.iting.lecture.model.LectureVO;

@Mapper
public interface LectureMapper {
	// 장효은
	public List<LectureVO> getAllLectureList(LectureVO vo, PagingVO pvo);
	public List<LectureVO> getLectureList(LectureVO vo);
	public LectureVO getLectureInfo(String ltNum);

	// 신수지
	public List<LectureVO> getTcList(String lecturerNum);	
	public List<LectureVO> getCurriList(LectureVO vo);
	public LectureVO getCurriAll(String ltNum);
	
  
	//도승민
	//등록
	public int ltInsert(LectureVO vo);
	public LectureVO getLectureInfo1(String ltNum);
	public int update(LectureVO vo);
	public List<LectureVO> endLectureList(LectureVO vo);
	public List<LectureVO> ingLectureList(LectureVO vo);
}
