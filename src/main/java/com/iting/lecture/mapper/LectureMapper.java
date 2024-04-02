package com.iting.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.lecture.model.LectureVO;

@Mapper
public interface LectureMapper {
	public List<LectureVO> getLectureList(LectureVO vo);
	public LectureVO getLectureInfo(String ltNum);
}
