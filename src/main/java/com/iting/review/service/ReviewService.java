package com.iting.review.service;

import java.util.Map;

import com.iting.review.model.RSearchVO;
import com.iting.review.model.ReviewVO;

public interface ReviewService {
	// 전체 리스트 조회.
	public Map<String, Object> getReviewList(ReviewVO vo, RSearchVO svo);

	// 리뷰 개수
	public long getCount(ReviewVO vo, RSearchVO svo);

}
