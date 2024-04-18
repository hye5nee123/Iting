package com.iting.save.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.save.model.SaveListVO;
import com.iting.save.model.SaveVO;

@Mapper
public interface SaveMapper {

	 //등록.
	public  int saveInsert(SaveVO vo);
	
	//이름조회
	public String memSelect(String id);
	
	public List<SaveListVO> getSaveList(String memNum); // 내가 찜한 강의 목록

	public int deleteSave(List<String> ltNums, String memNum); //내가 찜한 강의 삭제
}
