package com.iting.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.iting.common.model.UsersVO;



@Mapper
public interface UsersMapper {
//	MemberVO getMemberInfo(String userid);
//	TeacherVO getTeacherInfo(String userid);
	UsersVO getUserInfo(String userid);
}
