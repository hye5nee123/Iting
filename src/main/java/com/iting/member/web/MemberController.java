package com.iting.member.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iting.common.model.AccountVO;
import com.iting.member.model.MemberVO;
import com.iting.member.service.MemberService;
import com.iting.subsp.model.MySttlVO;
import com.iting.subsp.model.SubspVO;
import com.iting.subsp.service.SttlService;
import com.iting.subsp.service.SubspService;
import com.iting.tlsn.service.TlsnService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	TlsnService tlsnService;
	@Autowired
	SttlService sttlService;
	@Autowired
	SubspService subspService;
	
	// 내강의실
	@RequestMapping("member/myclass/list")
	public String getMemberList(Model model, MemberVO vo, HttpSession session) {
		String user = (String) session.getAttribute("usernum");
		model.addAttribute("testList", memberService.getMemberList());
		model.addAttribute("tlsnList", tlsnService.getTlsnList(user));
		return "member/myclass/list";
	}
	
	// 마이페이지 이동
	@GetMapping("/member/mypage")
	public String goMyMain(Model model, HttpSession session) {
		String usernum = (String) session.getAttribute("usernum");
		model.addAttribute("myinfo", memberService.getMyInfo(usernum));
		return "member/mypage/userInfo";
	}
	// 마이페이지 결제내역 이동
	@GetMapping("/member/mypage/sttlList")
	public String goMySttl(Model model, HttpSession session) {
		String usernum = (String) session.getAttribute("usernum");
		SubspVO subinfo = subspService.getSubspInfo(usernum);
		if(subinfo != null) {
			model.addAttribute("subspinfo", subinfo);
			List<MySttlVO> sttllist = sttlService.getSttlList(usernum);
			if(!sttllist.isEmpty()) {
				model.addAttribute("sttllist", sttllist);
			}
		} 
		return "member/mypage/sttlList";
	}
	// 마이페이지 회원정보 수정
	@ResponseBody
	@PutMapping("/member/updateMyInfo")
	public int updateMyInfo(@RequestBody AccountVO vo, HttpSession session) {
			vo.setAccnum((String) session.getAttribute("usernum"));
			int rescnt = memberService.putMyInfo(vo);
		return rescnt;
	}
}
