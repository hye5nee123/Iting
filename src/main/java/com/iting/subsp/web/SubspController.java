package com.iting.subsp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.iting.subsp.model.SubspVO;
import com.iting.subsp.service.SubspService;

@RestController
public class SubspController {
	@Autowired
	SubspService subspService;
	
	/* 회원 */
	
	// 구독 등록
	@PostMapping("/member/subsp/insert")
	public SubspVO subspInsert(@RequestBody SubspVO vo) {
		
		System.out.println(vo);
		
		subspService.subspInsert(vo);
		
		return vo;
	}
	
	// 구독 등록 화면 이동
	@RequestMapping("/member/subsp/form")
	public ModelAndView subspForm() {
		ModelAndView mv = new ModelAndView("/member/subsp/form");
		return mv;
	}
	
	// 내 구독 정보
	@RequestMapping("/member/subsp/info")
	public ModelAndView subspList() {
		ModelAndView mv = new ModelAndView("/admin/subsp/info");
		return mv;
	}
	
	// 구독정보 단건 조회
	@GetMapping("/member/subsp/{memNum}")
	public SubspVO memInfo(@PathVariable String memNum) {
		System.out.println(memNum);
		return subspService.getSubspInfo(memNum);
	}
	
	
	/* 강사 */
	
	
	
	/* 관리자 */
	
	//구독 전체 조회
	
}
