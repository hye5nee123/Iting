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
	public List<NoteVO> getNoteList() {
		return noteMapper.getNoteList();
	}
	
}
