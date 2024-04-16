package com.iting.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.common.model.AccountVO;
import com.iting.member.model.MemberVO;

@Mapper
public interface MemberMapper {
	public List<MemberVO> getMemberList();
	public List<MemberVO> getMemberLtsn();
	public MemberVO getMemberInfo(String memNum);
	public MemberVO getMyInfo(String memNum);
	public int putMyInfo(AccountVO vo);
}
