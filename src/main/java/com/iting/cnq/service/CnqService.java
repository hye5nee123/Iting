package com.iting.cnq.service;

import java.util.Map;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;

public interface CnqService {
	public Map<String, Object> getCnqList(CnqVO vo, CSearchVO svo);

	// 등록
	public int cnqInsert(CnqVO vo);

	// 상세조회
	public CnqVO getCnqInfo(String ltCnqNum);

	// 게시글 개수
	public long getCount(CnqVO vo, CSearchVO svo);

	// 수정
	public String updateCnq(CnqVO vo);

	// 삭제
	public int deleteCnq(String ltCnqNum);

	// 조회수 올리기
	public int updateHit(String ltCnqNum);
}
