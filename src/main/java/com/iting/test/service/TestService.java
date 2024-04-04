package com.iting.test.service;

import java.util.List;

import com.iting.test.model.TestVO;

public interface TestService {
	public List<TestVO> getTestList();
	public TestVO getTestInfo(String prblNum);
	public int insertTest(TestVO vo);
	public int deleteTest(String prblNum);
}
