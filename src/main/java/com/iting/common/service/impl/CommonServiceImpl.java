package com.iting.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.common.mapper.CommonMapper;
import com.iting.common.model.FileVO;
import com.iting.common.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonMapper commonMapper;

	@Override
	public int fileInsert(FileVO fvo) {
		return commonMapper.fileInsert(fvo);
	}

	@Override
	public FileVO getFileInfo(String fileNum) {
		return commonMapper.getFileInfo(fileNum);
	}

	@Override
	public List<FileVO> getFileInfoList(String fileNum) {
		return commonMapper.getFileInfoList(fileNum);
	}

}
