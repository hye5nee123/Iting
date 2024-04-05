package com.iting.note.service;

import java.util.List;

import com.iting.note.model.NoteVO;

public interface NoteService {
	public List<NoteVO> getNoteList();
	public int insertNote(NoteVO vo);
}
