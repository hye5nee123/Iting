package com.iting.note.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iting.note.model.NoteVO;

@Mapper
public interface NoteMapper {
	public List<NoteVO> getRecList();
	public List<NoteVO> getSentList(String user);
	public NoteVO getNoteInfo(String noteNum);
	public int insertNote(NoteVO vo);
}
