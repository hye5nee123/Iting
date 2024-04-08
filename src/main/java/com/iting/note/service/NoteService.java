package com.iting.note.service;

import java.util.List;

import com.iting.note.model.NoteVO;

public interface NoteService {
	public List<NoteVO> getRecList(String user);
	public List<NoteVO> getSentList(String user);
	public NoteVO getRecInfo(String noteNum);
	public NoteVO getSentInfo(String noteNum);
	public int insertNote(NoteVO vo);
}
