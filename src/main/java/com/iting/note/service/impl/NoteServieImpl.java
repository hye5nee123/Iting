package com.iting.note.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.note.mapper.NoteMapper;
import com.iting.note.model.NoteVO;
import com.iting.note.service.NoteService;

@Service
public class NoteServieImpl implements NoteService {

	@Autowired
	NoteMapper noteMapper;
	
	@Override
	public List<NoteVO> getRecList() {
		return noteMapper.getRecList();
	}
	
	@Override
	public List<NoteVO> getSentList(String user) {
		return noteMapper.getSentList(user);
	}
	
	@Override
	public NoteVO getNoteInfo(String noteNum) {
		return noteMapper.getNoteInfo(noteNum);
	}

	@Override
	public int insertNote(NoteVO vo) {
		return noteMapper.insertNote(vo);
	}


	
}
