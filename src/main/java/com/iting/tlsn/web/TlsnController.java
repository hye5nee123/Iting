package com.iting.tlsn.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iting.lecture.model.CurriVO;
import com.iting.lecture.service.CurriService;
import com.iting.lecture.service.LectureService;
import com.iting.subsp.model.SubspVO;
import com.iting.subsp.service.SubspService;
import com.iting.tlsn.model.TlsnDetailVO;
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
	
	@Autowired
	SubspService subspService;
		
	// 수강목록
	@RequestMapping("member/tlsn/list")
	public String getTlsnList(Model model, TlsnVO vo, HttpSession session) {
		String user = (String) session.getAttribute("usernum");
		model.addAttribute("tlsnList", tlsnService.getTlsnList(user));
		return "member/tlsn/list";
	}
	
	
	// 수강목록 상세목록
	@RequestMapping("member/tlsn/detailList/{ltNum}")
	public String getTlsnDetailList(@PathVariable String ltNum, Model model, TlsnVO vo, HttpSession session) {
		// 커리큘럼 정보
		CurriVO cvo = new CurriVO();
		cvo.setLtNum(ltNum);
		
		model.addAttribute("tlsn", curriService.getCurriList(cvo)); 		// 커리큘럼 정보
		model.addAttribute("lecture", lectureService.getLectureInfo(ltNum));// 강의 정보
		model.addAttribute("cur", lectureService.getCurriAll(ltNum));		// 커리큘럼 통계
		model.addAttribute("tlsnInfo", tlsnService.getTlsnInfo(vo));		// 강사 정보
		
		
		TlsnVO tlvo = TlsnVO.builder()
							.memNum((String) session.getAttribute("usernum"))
							.ltNum(ltNum)
							.build();
		
		TlsnVO tvo =  tlsnService.getTlsnInfoMem(tlvo); // 수강 정보
		
		
		// 수강 상세 - 진도율 정보
		model.addAttribute("curriDetail", tlsnService.getTlsnDtCurriInfo(tvo.getTlsnNum()));
		
		return "member/tlsn/detailList";
	}
	
	// 수강신청(등록)
	@PostMapping("member/tlsn/insert")
	@ResponseBody
	public int tlsnInsert(@RequestBody TlsnVO vo, HttpSession session) {
		String memNum = (String) session.getAttribute("usernum");
		
		/*
		// 구독 상태 조회
		SubspVO svo = subspService.getSubspInfo(memNum);
		
		System.out.println(svo.getSubspStCd());
		
		if(svo.getSubspStCd() == "m1") { // 구독중이 아니면 -99
			return -99;
		} else {
			
		}
		*/
		
		// 단건 조회
		TlsnVO tvo = tlsnService.getTlsnInfoMem(vo);
		
		if(tvo != null) {
			String stat = tvo.getTlsnStCd(); //수강 상태 코드
			
			if(stat.equals("j1")) { // 회원이 '수강중'이거나 '수강종료' 수강신청 X, 알려주기
				return -1;
			} else if (stat.equals("j2")) {
				return -2;
			}
		}
		// 수강 등록 + 수강상세 등록
		return tlsnService.tlsnInsert(vo);
		
	}
	
	// 수강 상세 진행률 수정
	@PutMapping("member/tlsn/detail/update")
	@ResponseBody
	public TlsnDetailVO tlsnDetailInsert(@RequestBody TlsnDetailVO vo) {
		
		tlsnService.tlsnDetailUpdate(vo);
		
		return vo;
	}
	
}
