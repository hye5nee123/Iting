package com.iting.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.iting.common.model.TeacherVO;
import com.iting.member.model.MemberVO;



@Mapper
public interface UsersMapper {
	MemberVO getMemberInfo(String userid);
	TeacherVO getTeacherInfo(String userid);
}
