package com.iting.sett.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.sett.model.SettVO;
import com.iting.sett.service.SettService;

@Controller
public class SettController {
	
	@Autowired
	SettService settService;
	
	// 정산 전체 조회
	@RequestMapping("admin/sett/list")
	public String settInfo(Model model, SettVO vo) {
		
		model.addAttribute("settList", settService.getSettList());
		model.addAttribute("settCount", settService.getSettCount());
		
		return "admin/sett/list";
	}
}
