package com.iting.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.lecture.model.AdminVO;

@Mapper
public interface AdminMapper {
		List<AdminVO> getTestList(AdminVO vo);
}
