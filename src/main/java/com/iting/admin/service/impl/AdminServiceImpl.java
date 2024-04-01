package com.iting.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.admin.mapper.AdminMapper;
import com.iting.admin.model.AdminVO;
import com.iting.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminMapper;
	
	@Override
	public List<AdminVO> getTestList(AdminVO vo) {
		return adminMapper.getTestList(vo);
	}
	
}
