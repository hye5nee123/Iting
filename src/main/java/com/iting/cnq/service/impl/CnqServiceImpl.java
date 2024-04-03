package com.iting.cnq.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.cnq.mapper.CnqMapper;
import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;
import com.iting.cnq.service.CnqService;

@Service
public class CnqServiceImpl implements CnqService {
	@Autowired
	CnqMapper cnqMapper;

	@Override
	public Map<String, Object> getCnqList(CnqVO vo, CSearchVO svo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cnqMapper.getCnqList(vo, svo));
		map.put("count", cnqMapper.getCount(vo, svo));
		return map;
	}

	@Override
	public int cnqInsert(CnqVO vo) {

		return cnqMapper.cnqInsert(vo);
	}

	@Override
	public CnqVO getCnqInfo(String ltCnqNum) {
		CnqVO vo = new CnqVO();
		vo.setLtCnqNum(ltCnqNum);
		return cnqMapper.getCnqInfo(ltCnqNum);
	}

	@Override
	public long getCount(CnqVO vo, CSearchVO svo) {

		return cnqMapper.getCount(vo, svo);
	}

	@Override
	public int updateCnq(CnqVO vo) {
		
		return cnqMapper.updateCnq(vo);
	}

}
