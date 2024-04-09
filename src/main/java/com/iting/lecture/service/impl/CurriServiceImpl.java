package com.iting.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.lecture.mapper.CurriMapper;
import com.iting.lecture.model.CurriVO;
import com.iting.lecture.service.CurriService;
@Service
public class CurriServiceImpl implements CurriService{
	
	@Autowired
	CurriMapper curriMapper;

	@Override
	public List<CurriVO> getCurriList(CurriVO vo) {
		return curriMapper.getCurriList(vo);
	}

	
	@Override
	public CurriVO getCurriInfo(String rndNum) {
		CurriVO vo = new CurriVO();
		vo.setRndNum(rndNum);
		return curriMapper.getCurriInfo(rndNum);
	}

}
