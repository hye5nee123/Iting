package com.iting.note.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.note.model.NoteVO;
import com.iting.note.service.NoteService;

@Controller
public class NoteController {
	
	@Autowired
	NoteService noteService;
	
	// 목록조회
		@RequestMapping("teacher/note/list")
		public String getNoteList(Model model, NoteVO vo) {
			model.addAttribute("testList", noteService.getNoteList());
			return "teacher/note/list";
		}
}
