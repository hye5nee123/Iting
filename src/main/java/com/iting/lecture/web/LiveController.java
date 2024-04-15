package com.iting.lecture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iting.lecture.model.LiveVO;
import com.iting.lecture.service.LiveService;

@Controller
public class LiveController {

	@Autowired
	LiveService liveService;

	//목록 조회
	@GetMapping("/teacher/live/getLiveList")
	public String list(Model model, LiveVO vo) {
		model.addAttribute("getLiveList", liveService.getLiveList(vo));
		return "teacher/live/getLiveList";
	}
}
	
