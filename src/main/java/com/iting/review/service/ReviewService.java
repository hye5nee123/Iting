package com.iting.review.service;

import java.util.List;
import java.util.Map;

import com.iting.review.model.RSearchVO;
import com.iting.review.model.ReviewVO;

public interface ReviewService {
	// 전체 리스트 조회.
	public List<ReviewVO> getReviewList(ReviewVO vo);

	// 리뷰 개수
	public long getCount(ReviewVO vo, RSearchVO svo);

}
