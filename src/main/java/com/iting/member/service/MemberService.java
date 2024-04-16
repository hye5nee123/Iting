package com.iting.member.service;

import java.util.List;

import com.iting.common.model.AccountVO;
import com.iting.member.model.MemberVO;

public interface MemberService {
	public List<MemberVO> getMemberList();
	public List<MemberVO> getMemberLtsn(MemberVO vo);
	public MemberVO getMemberInfo(String memNum);
	MemberVO getMyInfo(String memNum);
	int putMyInfo(AccountVO vo);
	public MemberVO getMemberNote(MemberVO vo);
}
