package com.iting.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iting.common.mapper.UsersMapper;
import com.iting.common.model.UsersVO;
import com.iting.common.security.CustomUsers;
import com.iting.common.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

	@Autowired
	UsersMapper usersMapper;

	@Override
	public UsersVO getUserInfo(String userid) {
		return usersMapper.getUserInfo(userid);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersVO userVO = usersMapper.getUserInfo(username);
		if (userVO == null) {
			throw new UsernameNotFoundException("id not found");
		}
		return new CustomUsers(userVO);
	}
}
