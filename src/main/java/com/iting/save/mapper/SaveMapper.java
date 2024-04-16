package com.iting.save.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.iting.save.model.SaveVO;

@Mapper
public interface SaveMapper {

	 //등록.
	public  int saveInsert(SaveVO vo);
	
	//이름조회
	public String memSelect(String id);
}
