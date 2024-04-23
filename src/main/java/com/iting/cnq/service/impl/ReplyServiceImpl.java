package com.iting.cnq.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.cnq.mapper.CmtMapper;
import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;
import com.iting.cnq.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private CmtMapper cmtMapper;

	@Override
	public Map<String, Object> getList(CnqVO vo, CSearchVO svo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cmtMapper.getListWithPaging(vo, svo));
		map.put("count", cmtMapper.getCount(vo));
		return map;
	}
	//등록(ajax)
	@Override
	public int cmtInsert(CnqVO vo) {
		return cmtMapper.cmtInsert(vo);
	}
	@Override
	public long getCount(CnqVO vo) {
		
		return cmtMapper.getCount(vo);
	}

}
