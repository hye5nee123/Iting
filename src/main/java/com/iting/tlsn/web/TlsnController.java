package com.iting.tlsn.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.lecture.model.CurriVO;
import com.iting.lecture.service.CurriService;
import com.iting.lecture.service.LectureService;
import com.iting.tlsn.model.TlsnVO;
import com.iting.tlsn.service.TlsnService;

/**
 * 
 * @author 신수지
 * 학생의 수강신청 목록
 */
@Controller
public class TlsnController {

	@Autowired
	TlsnService tlsnService;
	
	@Autowired
	LectureService lectureService;
	
	@Autowired
	CurriService curriService;
		
	// 수강목록
	@RequestMapping("member/tlsn/list")
	public String getTlsnList(Model model, TlsnVO vo, HttpSession session) {
		String user = (String) session.getAttribute("usernum");
		model.addAttribute("tlsnList", tlsnService.getTlsnList(user));
		return "member/tlsn/list";
	}
	
	
	// 수강목록상세
	@RequestMapping("member/tlsn/detailList/{ltNum}")
	public String getTlsnDetailList(@PathVariable String ltNum, Model model, TlsnVO vo) {
		// 커리큘럼 정보
		CurriVO cvo = new CurriVO();
		cvo.setLtNum(ltNum);
		model.addAttribute("tlsn", curriService.getCurriList(cvo));
		// 강사정보
		model.addAttribute("tlsnInfo", tlsnService.getTlsnInfo(vo));
		System.out.println(vo);
		return "member/tlsn/detailList";
	}
}
