package com.iting.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.test.model.TestVO;

@Mapper
public interface TestMapper {
	public List<TestVO> getTestList(String ltNum);
	public TestVO getTestInfo(String prblNum);
	public int insertTest(TestVO vo);
	public int updateTest(TestVO testVO);
	public int deleteTest(String prblNum);
	
	// 회원 문제응시
	public int insertExam(TestVO vo);
	public int insertExamDetail(TestVO vo);
	public int deleteExam(TestVO vo);
	public int deleteExamDetail(TestVO vo);
	public List<TestVO> getExamList(TestVO vo);
	public int updateExam(TestVO testVO);
	public int updateResult(TestVO testVO);
	public List<TestVO> getExamResult(TestVO vo);
	
	// 채점
	public int updateScore(TestVO vo);
}
