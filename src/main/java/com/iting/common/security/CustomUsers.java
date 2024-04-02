package com.iting.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.iting.common.model.UsersVO;

import lombok.Getter;

@Getter
public class CustomUsers implements UserDetails{
	
	private UsersVO usersVO;
	
	public CustomUsers(UsersVO usersVO) {
		this.usersVO = usersVO;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authlist = new ArrayList<>();
		authlist.add(new SimpleGrantedAuthority(usersVO.getUserCd()));
		return authlist;
	}

	@Override
	public String getPassword() {
		return usersVO.getPw();
	}

	@Override
	public String getUsername() {
		return usersVO.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
