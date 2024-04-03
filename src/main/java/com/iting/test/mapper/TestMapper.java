package com.iting.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.test.model.TestVO;

@Mapper
public interface TestMapper {
	public List<TestVO> getTestList();
	public int insertTest(TestVO vo);
}
