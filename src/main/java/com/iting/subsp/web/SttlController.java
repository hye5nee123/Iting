package com.iting.subsp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iting.subsp.model.SttlVO;
import com.iting.subsp.model.SubspVO;
import com.iting.subsp.service.SttlService;

@RestController
public class SttlController {
	@Autowired
	SttlService sttlService;
	
	/* 회원 */
	
	// 결제 등록
	@PostMapping("/member/sttl/insert")
	public SttlVO sttlInsert(@RequestBody SttlVO vo) {
		
		System.out.println(vo);
		
		sttlService.sttlInsert(vo);
		
		return vo;
	}
	
	// 빌링키 발급
	@PostMapping("/member/sttl/billing")
	public SubspVO subspBilling(@RequestBody SubspVO vo) {
		
		System.out.println(vo);
		
		return vo;
	}
	
	
	/* 강사 */
	
	
	
	/* 관리자 */
	
}