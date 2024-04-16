package com.iting.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.member.mapper.MemberMapper;
import com.iting.member.model.MemberVO;
import com.iting.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;

	@Override
	public List<MemberVO> getMemberList() {
		return memberMapper.getMemberList();
	}

	@Override
	public List<MemberVO> getMemberLtsn(MemberVO vo) {
		return memberMapper.getMemberLtsn(vo);
	}

	@Override
	public MemberVO getMemberInfo(String memNum) {
		return memberMapper.getMemberInfo(memNum);
	}

	@Override
	public MemberVO getMemberNote(MemberVO vo) {
		return memberMapper.getMemberNote(vo);
	}




}
