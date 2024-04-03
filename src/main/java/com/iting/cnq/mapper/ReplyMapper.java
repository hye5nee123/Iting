package com.iting.cnq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;
import com.iting.cnq.model.ReplyVO;

@Mapper
public interface ReplyMapper {
	public List<ReplyVO> replyList(ReplyVO vo);

	public long getCount(CnqVO vo, CSearchVO svo);
}
