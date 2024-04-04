package com.iting.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.test.model.TestVO;

@Mapper
public interface TestMapper {
	public List<TestVO> getTestList();
	public TestVO getTestInfo(String prblNum);
	public int insertTest(TestVO vo);
	public int updateTest(TestVO testVO);
	public int deleteTest(String prblNum);
}
