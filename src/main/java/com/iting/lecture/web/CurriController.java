package com.iting.lecture.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iting.common.FileUtil;
import com.iting.common.model.FileVO;
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
	//커리등록
	@GetMapping("/teacher/curri/insert")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/teacher/curri/insert");
		return mv;
	}
	//등록기능
	@ResponseBody
	@PostMapping("/teacher/curri/insert1")
	public String curriInsert(@RequestBody CurriVO vo,MultipartFile uFile) throws IllegalStateException, IOException {
		curriService.CurriInsert(vo);
		FileVO fvo = FileUtil.uploadFile(uFile);
		return "";
	}

}
