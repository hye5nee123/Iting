package com.iting.teacher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.teacher.mapper.TeacherMapper;
import com.iting.teacher.model.TeacherVO;
import com.iting.teacher.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherMapper teacherMapper;
	

	@Override
	public TeacherVO getTeacherInfo(String ltNum) {
		return teacherMapper.getTeacherInfo(ltNum);
	}

}
