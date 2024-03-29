package com.example.iting.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.iting.test.TestVO;
import com.example.iting.test.mapper.TestMapper;
import com.example.iting.test.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestMapper testMapper;
	
	@Override
	public List<TestVO> getTestList(TestVO vo) {
		return testMapper.getTestList(vo);
	}
}
