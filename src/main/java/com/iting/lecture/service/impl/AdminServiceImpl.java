package com.iting.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.lecture.mapper.AdminMapper;
import com.iting.lecture.model.AdminVO;
import com.iting.lecture.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminMapper;
	
	@Override
	public List<AdminVO> getTestList(AdminVO vo) {
		return adminMapper.getTestList(vo);
	}
	
}
