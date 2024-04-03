package com.iting.cnq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.cnq.mapper.ReplyMapper;
import com.iting.cnq.model.ReplyVO;
import com.iting.cnq.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper replyMapper;

	// list 조회.
	@Override
	public List<ReplyVO> replyList(ReplyVO vo) {

		return replyMapper.replyList(vo);
	}

}
