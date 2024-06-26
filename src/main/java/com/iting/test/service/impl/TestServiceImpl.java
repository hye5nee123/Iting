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
	public TestVO getTestInfo(String ltNum, String prblNum) {
		return testMapper.getTestInfo(ltNum, prblNum);
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
	public int deleteTest(String ltNum, String prblNum) {
		return testMapper.deleteTest(ltNum, prblNum);
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

	// 문제응시
	@Override
	public int updateExam(List<TestVO> list) {
		String no = "";
		for(int i = 0; i < list.size(); i++) {			
			testMapper.updateExam(list.get(i));
			testMapper.updateResult(list.get(i));
			no = list.get(i).getApplexamNum();
		}
		// 총점 업데이트 : 응시번호 기준으로 정답(1)인 친구들을 뽑아서 응시테이블에 시험점수와 합격여부를 삽입
		testMapper.updateScore(no);
		return 1;
	}

	// 응시결과
	@Override
	public TestVO getExamResult(String applexamNum) {
		return testMapper.getExamResult(applexamNum);
	}
	
	// 채점결과
	@Override
	public List<TestVO> getResultList(TestVO vo) {
		return testMapper.getResultList(vo);
	}

	// 오답노트
	@Override
	public List<TestVO> getOxList(TestVO vo) {
		return testMapper.getOxList(vo);
	}

	
	

	

	
}
