package com.iting.test.service;

import java.util.List;

import com.iting.test.model.TestVO;

public interface TestService {
	public List<TestVO> getTestList(String ltNum);
	public TestVO getTestInfo(String prblNum);
	public int insertTest(TestVO vo);
	public int updateTest(TestVO testVO);
	public int deleteTest(String prblNum);
	
	// 회원 문제응시
	public List<TestVO> insertExam(TestVO vo);
	public int updateExam(List<TestVO> testVO);
	
	// 응시결과
	public TestVO getExamResult(String applexamNum);
	public List<TestVO> getResultList(TestVO vo);
}
