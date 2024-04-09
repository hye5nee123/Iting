package com.iting.lecture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iting.lecture.model.CurriVO;
import com.iting.lecture.service.CurriService;

@Controller
public class CurriController {
	
	@Autowired
	CurriService curriService;
	//커리 목록조회
	@GetMapping("/teacher/curri/list")
	public String list(Model model, CurriVO vo) {
		model.addAttribute("list", curriService.getCurriList(vo));
		return "/teacher/curri/list";
	}
	//단건조회
	@GetMapping("/teacher/curri/info/{rndNum}")
	public String info(@PathVariable String rndNum, Model model) {
		model.addAttribute("curri", curriService.getCurriInfo(rndNum));
		curriService.getCurriInfo(rndNum);
		return "/teacher/curri/info";
	}
	

}
