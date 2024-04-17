package com.iting.cnq.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;
import com.iting.cnq.service.CnqService;
import com.iting.common.model.PagingVO;
import com.iting.common.service.CommonService;

import lombok.RequiredArgsConstructor;

/**
 * 강의에 대한 커뮤니티 사용.
 * 
 * @author 조혜원
 *
 */

@RequiredArgsConstructor
@Controller
public class CnqController {

	@Autowired
	CnqService cnqService;
	@Autowired
	CommonService commonService;

	/* 회원 */ //

	// 강의문답 전체조회. //
	@RequestMapping("/member/cnq/list/{ltNum}")
	public String cnqList(@PathVariable String ltNum, Model model, CnqVO vo, CSearchVO svo, PagingVO pvo) {
		// paging처리.
		pvo.setPageUnit(5); // 데이터수
		pvo.setPageSize(3); // 페이지번호
		svo.setStart(pvo.getFirst());
		svo.setEnd(pvo.getLast());
		vo.setLtNum(ltNum);
		System.out.println(">>>>>>>>>>>> " + vo + ":" + svo);
		Map<String, Object> map = cnqService.getCnqList(vo, svo);
		pvo.setTotalRecord((long) map.get("count"));

		model.addAttribute("paging", pvo);
		model.addAttribute("cnqList", map.get("data"));
		return "member/cnq/list";

	}

	// info 조회.
	@RequestMapping("member/cnq/info/{ltCnqNum}")
	public String cnqInfo(@PathVariable String ltCnqNum, Model model) {
		// Cnq 조회
		CnqVO vo = cnqService.getCnqInfo(ltCnqNum);
		model.addAttribute("cnq", vo);

		// 조회 
		cnqService.updateHit(ltCnqNum);

		// 첨부파일 조회
		if (vo.getAtchNum() != null) {
			model.addAttribute("files", commonService.getFileInfoList(vo.getAtchNum()));
		}

		return "member/cnq/info";

	}

	// 등록 페이지로 이동.
	@GetMapping("member/cnq/insert/{ltNum}")
	public ModelAndView list(@PathVariable String ltNum) {
		ModelAndView mv = new ModelAndView("member/cnq/insert");
		mv.addObject("ltNum", ltNum);
		return mv;
	}

	// 등록 기능.
	@ResponseBody
	@PostMapping("/member/cnq/insert")
	// jsonType 을 받기 위해서 @RequestBody붙임.
	//usernum을 받아오기 위해서 session 값 가져옴.
	public CnqVO cnqInsert(@RequestBody CnqVO vo, HttpSession session) {
		vo.setMemNum((String) session.getAttribute("usernum"));
		System.out.println(vo + "====================");
		cnqService.cnqInsert(vo);
		return vo;

	}

	// 수정 form 이동.
	@GetMapping("member/cnq/updateform/{ltCnqNum}")
	public String update(@PathVariable String ltCnqNum, Model model) {
		model.addAttribute("cnq", cnqService.getCnqInfo(ltCnqNum));
		return "member/cnq/update";

	}

	// 수정 기능.
	@ResponseBody
	@PostMapping("/member/cnq/update/{ltCnqNum}")
	public String updateCnq(@RequestBody CnqVO vo) {
		cnqService.updateCnq(vo);
		return "redirect:/member/cnq/list";
	}

// 삭제처리.
	@RequestMapping("/member/cnq/{ltCnqNum}")
	public String delete(@PathVariable String ltCnqNum) {
		cnqService.deleteCnq(ltCnqNum);
		return "redirect:/member/cnq/list";
	}

	/* 강사 */

	/* 관리자 */
	
	// 강의문답 전체조회. //
	@RequestMapping("/admin/cnq/list")
	public String getAdminCnqList(Model model, CnqVO vo) {
		model.addAttribute("adminCnqList", cnqService.getAdminCnqList(vo));
		return "admin/community/list";

	}

}
