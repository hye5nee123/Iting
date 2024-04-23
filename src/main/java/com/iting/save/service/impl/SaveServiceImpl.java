package com.iting.save.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.cnq.mapper.CnqMapper;
import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;
import com.iting.cnq.service.CnqService;
import com.iting.save.mapper.SaveMapper;
import com.iting.save.model.SaveListVO;
import com.iting.save.model.SaveVO;
import com.iting.save.service.SaveService;

@Service
public class SaveServiceImpl implements SaveService {
	@Autowired
	SaveMapper saveMapper;

	@Override
	public int saveInsert(SaveVO vo) {
		// TODO Auto-generated method stub
		return saveMapper.saveInsert(vo);
	}

	@Override
	public String memSelect(String id) {
		// TODO Auto-generated method stub
		return saveMapper.memSelect(id);
	}

	@Override
	public List<SaveListVO> getSaveList(String memNum) {
		return saveMapper.getSaveList(memNum);
	}

	@Override
	public int deleteSave(List<String> ltNums, String memNum) {
		return saveMapper.deleteSave(ltNums, memNum);
	}

	

}
