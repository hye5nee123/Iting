package com.iting.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.lecture.model.LectureVO;

@Mapper
public interface LectureMapper {
	public List<LectureVO> getLectureList(LectureVO vo);
	public LectureVO getLectureInfo(String ltNum);
	public LectureVO getLectureInfo1(String ltNum);
	public List<LectureVO> getTcList(String lecturerNum);


}
