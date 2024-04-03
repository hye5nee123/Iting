package com.iting.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.test.mapper.TestMapper;
import com.iting.test.model.TestVO;
import com.iting.test.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestMapper testMapper;

	@Override
	public List<TestVO> getTestList() {
		return testMapper.getTestList();
	}

	@Override
	public int insertTest(TestVO vo) {
		return testMapper.insertTest(vo);
	}
}
