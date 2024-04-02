package com.iting.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.iting.common.model.MemberVO;
import com.iting.common.model.TeacherVO;



@Mapper
public interface UsersMapper {
	MemberVO getMemberInfo(String userid);
	TeacherVO getTeacherInfo(String userid);
}
