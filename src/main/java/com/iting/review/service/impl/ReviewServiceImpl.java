package com.iting.review.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.review.mapper.ReviewMapper;
import com.iting.review.model.RSearchVO;
import com.iting.review.model.ReviewVO;
import com.iting.review.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewMapper reviewMapper;



	@Override
	public long getCount(ReviewVO vo, RSearchVO svo) {

		return reviewMapper.getCount(vo, svo);
	}

	@Override
	public List<ReviewVO> getReviewList(ReviewVO vo) {
		
		return reviewMapper.getReviewList(vo);
	}

}
