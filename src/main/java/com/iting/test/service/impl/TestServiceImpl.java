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
	public List<TestVO> getTestList(String ltNum) {
		return testMapper.getTestList(ltNum);
	}
	
	@Override
	public TestVO getTestInfo(String prblNum) {
		return testMapper.getTestInfo(prblNum);
	}
	
	@Override
	public int insertTest(TestVO vo) {
		return testMapper.insertTest(vo);
	}

	@Override
	public int updateTest(TestVO testVO) {
		return testMapper.updateTest(testVO);
	}
	
	@Override
	public int deleteTest(String prblNum) {
		return testMapper.deleteTest(prblNum);
	}

	@Override
	public List<TestVO> insertExam(TestVO vo) {
		// 문제가 있으면 삭제
		testMapper.deleteExamDetail(vo);
		testMapper.deleteExam(vo);
		// 문제등록
		testMapper.insertExam(vo); // detail 정보등록
		testMapper.insertExamDetail(vo);
		
		// 문제조회
		return testMapper.getExamList(vo);
	}

}
