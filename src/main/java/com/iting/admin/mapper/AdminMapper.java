package com.iting.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.admin.model.AdminVO;

@Mapper
public interface AdminMapper {
		List<AdminVO> getTestList(AdminVO vo);
}
