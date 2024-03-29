package com.example.iting.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.iting.test.TestVO;

@Mapper
public interface TestMapper {
	List<TestVO> getTestList(TestVO vo);
}
