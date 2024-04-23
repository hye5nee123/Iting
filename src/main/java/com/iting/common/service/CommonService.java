package com.iting.common.service;

import java.util.List;

import com.iting.common.model.FileVO;

public interface CommonService {
	int fileInsert(FileVO fvo); // 첨부파일 등록
	FileVO getFileInfo(String fileNum); // 첨부파일 단건조회
	List<FileVO>  getFileInfoList(String fileNum); // 첨부파일 단건조회
}
