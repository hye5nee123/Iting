package com.iting.member.service;

import java.util.List;

import com.iting.member.model.MemberVO;

public interface MemberService {
	public List<MemberVO> getMemberList();
	public List<MemberVO> getMemberLtsn(MemberVO vo);
	public MemberVO getMemberInfo(String memNum);
	public MemberVO getMemberNote(MemberVO vo);
}
