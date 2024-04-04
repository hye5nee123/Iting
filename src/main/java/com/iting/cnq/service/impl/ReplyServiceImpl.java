package com.iting.cnq.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.cnq.mapper.ReplyMapper;
import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;
import com.iting.cnq.service.ReplyService;

import lombok.Setter;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper replyMapper;

	@Override
	public Map<String, Object> getList(CnqVO vo, CSearchVO svo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", replyMapper.getListWithPaging(vo, svo));
		map.put("count", replyMapper.getCount(vo));
		return map;
	}

}
