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
	public List<NoteVO> getRecList(String user) {
		return noteMapper.getRecList(user);
	}
	
	@Override
	public List<NoteVO> getSentList(String user) {
		return noteMapper.getSentList(user);
	}
	
	@Override
	public NoteVO getRecInfo(String noteNum) {
		return noteMapper.getRecInfo(noteNum);
	}

	@Override
	public NoteVO getSentInfo(String noteNum) {
		return noteMapper.getRecInfo(noteNum);
	}
	
	@Override
	public int insertNote(NoteVO vo) {
		return noteMapper.insertNote(vo);
	}

	@Override
	public List<NoteVO> getMemNoteList(String user) {
		return noteMapper.getMemNoteList(user);
	}



	
}
