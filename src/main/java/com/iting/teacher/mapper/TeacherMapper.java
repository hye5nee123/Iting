package com.iting.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.iting.teacher.model.TeacherVO;

@Mapper
public interface TeacherMapper {
	public TeacherVO getTeacherInfo(String ltNum);
}
