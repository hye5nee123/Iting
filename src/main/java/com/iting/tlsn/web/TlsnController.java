package com.iting.tlsn.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iting.tlsn.model.TlsnVO;
import com.iting.tlsn.service.TlsnService;

@Controller
public class TlsnController {

	@Autowired
	TlsnService tlsnService;
	
	@Autowired
	private HttpSession httpSession;
	
	// 수강목록
	@RequestMapping("member/tlsn/list")
	public String getTlsnList(Model model, TlsnVO vo) {
		model.addAttribute("tlsnList", tlsnService.getTlsnList());
		return "member/tlsn/list";
	}
	
	// 수강목록상세
	@RequestMapping("member/tlsn/detailList/{ltNum}")
	public String getTlsnDetailList(@PathVariable String ltNum, Model model, TlsnVO vo) {
		model.addAttribute("tlsn", tlsnService.getTlsnDetailList(ltNum));
		String user = (String) httpSession.getAttribute("usernum");
		System.out.println(user);
		return "member/tlsn/detailList";
	}
	
	// 수강신청(등록)
	@PostMapping("member/tlsn/insert")
	@ResponseBody
	public TlsnVO tlsnInsert(@RequestBody TlsnVO vo) {
		
		TlsnVO tvo = tlsnService.getTlsnInfo(vo);
		
		System.out.println(tvo + "=============================");
		
		String stat = "";
		
		if(tvo != null) {
			
			stat = tvo.getTlsnStCd(); //수강 상태 코드
			
			if(stat.equals("j1")) { // 회원이 '수강중'이면 수강신청 X, 알려주기
				
				tvo.setRetCode("ING");
				
				return tvo;
				
			} else { // 회원이 '수강종료'이면 수강신청 X, 알려주기
				
				tvo.setRetCode("FIN");
				
				return tvo;
			}
			
		} else { // 회원이 '수강전'이면 수강신청하기
			
			tlsnService.tlsnInsert(vo);
			
			return vo;
		}
	}
}
