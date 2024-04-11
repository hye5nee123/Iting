package com.iting.review.service.impl;

import java.util.HashMap;
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
	public Map<String, Object> getReviewList(ReviewVO vo, RSearchVO svo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", reviewMapper.getReviewList(vo, svo));
		map.put("count", reviewMapper.getCount(vo, svo));
		return map;
	}
	@Override
	public long getCount(ReviewVO vo, RSearchVO svo) {
		
		return reviewMapper.getCount(vo, svo);
	}

}
