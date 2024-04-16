package com.iting.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.member.model.MemberVO;

@Mapper
public interface MemberMapper {
	public List<MemberVO> getMemberList();
	public List<MemberVO> getMemberLtsn(MemberVO vo);
	public MemberVO getMemberInfo(String memNum);
	public MemberVO getMemberNote(MemberVO vo);
}
