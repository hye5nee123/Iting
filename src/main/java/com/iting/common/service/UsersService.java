package com.iting.common.service;

import com.iting.common.model.AccountVO;
import com.iting.common.model.UsersVO;

public interface UsersService {
	public UsersVO getUserInfo(String userid);
	public int insertUser(AccountVO acvo);
}
