package com.iting.save.service;

import java.util.List;
import java.util.Map;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;
import com.iting.save.model.SaveListVO;
import com.iting.save.model.SaveVO;

public interface SaveService {

	// 등록
	public  int saveInsert(SaveVO vo);
	
	//이름조회
	public String memSelect(String id);
	
	List<SaveListVO> getSaveList(String memNum);
}
