package com.iting.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.cnq.model.CnqVO;
import com.iting.review.model.RSearchVO;
import com.iting.review.model.ReviewVO;

@Mapper
public interface ReviewMapper {

	// 전체 리스트 조회.
	public List<CnqVO> getReviewList(ReviewVO vo, RSearchVO svo);
	//글 개수 구하기.
	public long getCount(ReviewVO vo, RSearchVO svo);

}
