package com.iting.tlsn.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
