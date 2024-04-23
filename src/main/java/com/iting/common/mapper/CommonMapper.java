package com.iting.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.common.model.FileVO;

@Mapper
public interface CommonMapper {
	int fileInsert(FileVO fvo); // 첨부파일 등록
	FileVO getFileInfo(String fileNum); // 첨부파일 단건조회
	List<FileVO>  getFileInfoList(String fileNum); // 첨부파일 단건조회
}
