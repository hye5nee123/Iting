package com.iting.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.iting.common.model.AccountVO;
import com.iting.common.model.UsersVO;



@Mapper
public interface UsersMapper {
	UsersVO getUserInfo(String userid);
	int insertUser(AccountVO acvo);
	String getUserNum();
}
