package com.iting.subsp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iting.subsp.model.SttlVO;
import com.iting.subsp.service.ReqPaymentScheduler;
import com.iting.subsp.service.SttlService;

@RestController
public class SttlController {
	@Autowired
	SttlService sttlService;
	
	@Autowired
	ReqPaymentScheduler ReqPaymentSchedulerTest;
	
	/* 회원 */
	
	// 결제 등록
	@PostMapping("/member/sttl/insert")
	public SttlVO sttlInsert(@RequestBody SttlVO vo) {
		
		System.out.println(vo);
		
		return vo;
	}
	
	// 빌링키 DB등록
	@PostMapping("/member/sttl/billing")
	public SttlVO subspBilling(@RequestBody SttlVO vo) {
		
		sttlService.sttlInsert(vo); // 빌링키 + 회원번호 등록
		System.out.println(vo);
		
		return vo;
	}
	
	// 2회차 이상 결제 (정기결제 테스트)
	//@Scheduled(cron="0 0/2 * * * ?")
	@GetMapping("/member/test")
    public HttpEntity<String> getPostTest(){
		return ReqPaymentSchedulerTest.setPayScheduleTest();
       
    }
	
	
	/* 강사 */
	
	
	
	/* 관리자 */
	
}
