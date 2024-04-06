package com.iting.cnq.web;

import java.util.Map;

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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CnqController {

	@Autowired
	CnqService cnqService;

	/* 회원 */ //

	// 강의문답 전체조회. //
	@RequestMapping("/member/cnq/list")
	public String cnqList(Model model, CnqVO vo, CSearchVO svo, PagingVO pvo) {
		// paging처리.
		pvo.setPageUnit(5); // 데이터수
		pvo.setPageSize(3); // 페이지번호
		svo.setStart(pvo.getFirst());
		svo.setEnd(pvo.getLast());
		System.out.println(">>>>>>>>>>>> " + vo + ":" + svo);
		Map<String, Object> map = cnqService.getCnqList(vo, svo);
		pvo.setTotalRecord((long) map.get("count"));
		model.addAttribute("paging", pvo);
		model.addAttribute("cnqList", map.get("data"));
		return "member/cnq/list";

	}

	// info 조회.
	@RequestMapping("member/cnq/info/{l" + "tCnqNum}")
	public String cnqInfo(@PathVariable String ltCnqNum, Model model) {
		String a = cnqService.getCnqInfo(ltCnqNum).getLtCnqNum();
//		model.addAttribute("댓글", ReplyService.class);
		model.addAttribute("cnq", cnqService.getCnqInfo(ltCnqNum));
		System.out.println("조회완료");
		cnqService.updateHit(ltCnqNum);
		return "member/cnq/info";

	}

	// 데이터 가져오기.
//	@ResponseBody
//	@GetMapping("/fetch/cnqList")
//	public List<CnqVO> getCnqList(CnqVO vo) {
//		return cnqService.cnqList(vo);
//	}
	// 강의문답 단건조회.
//	@RequestMapping("member/cnqInfo/{ltCnqNum}")
//	public String cnqInfo(@PathVariable String ltCnqNum, Model model) {
//		model.addAttribute("cnq" , cnqService.)
//		return ltCnqNum;
//
//	}

	// 등록 페이지로 이동.
	@GetMapping("member/cnq/insert")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("member/cnq/insert");
		return mv;
	}

	// 등록 기능.
	@ResponseBody
	@PostMapping("/member/cnq/insert")
	// jsonType 을 받기 위해서 @RequestBody붙임.
	public CnqVO cnqInsert(@RequestBody CnqVO vo) {
		vo.setMemNum("me00001");
		vo.setLtNum("lt00001");
		System.out.println(vo + "====================");
		cnqService.cnqInsert(vo);
		return vo;
		
	}

	// 수정 form 이동.
	@GetMapping("/member/cnq/updateform")
	public ModelAndView updateForm() {
		ModelAndView mv = new ModelAndView("member/cnq/update");
		return mv;
	}

// 수정 기능.
//	@PutMapping("/member/cnq/update")
//	public String updateCnq(CnqVO vo) {
//		if (cnqService.updateCnq(vo) > 0) {
//			System.out.println("수정완료");
//		}
//
//		return "redirect:/member/cnq/list";
//	}

// 삭제처리.
	@RequestMapping("/member/cnq/{ltCnqNum}")
	public String delete(@PathVariable String ltCnqNum) {
		cnqService.deleteCnq(ltCnqNum);
		return "redirect:/member/cnq/list";
	}

	/* 강사 */

	/* 관리자 */
}
