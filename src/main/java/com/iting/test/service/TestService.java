package com.iting.test.service;

import java.util.List;

import com.iting.test.model.TestVO;

public interface TestService {
	public List<TestVO> getTestList(TestVO vo);
}
