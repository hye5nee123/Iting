package com.iting.cnq.web;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;
import com.iting.cnq.model.PagingVO;
import com.iting.cnq.service.CnqService;
import com.iting.cnq.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CnqController {

	@Autowired
	CnqService cnqService;

	/* 회원 */

	// 강의문답 전체조회.
	@RequestMapping("member/cnq/list")
	public String cnqList(Model model, CnqVO vo, CSearchVO svo, PagingVO pvo) {
		// paging처리.
		pvo.setPageUnit(5); // 데이터수
		pvo.setPageSize(3); // 페이지번호
		svo.setStart(pvo.getFirst());
		svo.setEnd(pvo.getLast());

		Map<String, Object> map = cnqService.getCnqList(vo, svo);
		pvo.setTotalRecord((long) map.get("count"));
		model.addAttribute("paging", pvo);
		model.addAttribute("cnqList", map.get("data"));
		return "member/cnq/list";
	}

	// info 조회.
	@RequestMapping("member/cnq/info/{ltCnqNum}")
	public String cnqInfo(@PathVariable String ltCnqNum, Model model) {
		String a = cnqService.getCnqInfo(ltCnqNum).getLtCnqNum();

		model.addAttribute("댓글", ReplyService.class);
		model.addAttribute("cnq", cnqService.getCnqInfo(ltCnqNum));
		System.out.println("조회완료");
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
	@GetMapping("member/cnq/form")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("member/cnq/form");
		return mv;
	}

	// 등록 기능.......
	@PostMapping("/cnq/insert")
	public String insert(CnqVO vo, MultipartFile photofile) throws IllegalStateException, IOException {
		if (photofile != null) {
			if (photofile.getSize() > 0) {
				// 파일 생성
				File file = new File("c:/upload", photofile.getOriginalFilename());
				// 파일 저장
				photofile.transferTo(file);
				vo.setImage(photofile.getOriginalFilename());
			}

		}
		int result = cnqService.cnqInsert(vo);
		if (result > 0) {
			System.out.println("등록완료");
		}
		return "redirect:member/cnq/list";

	}

	// 수정

	// 삭제

	/* 강사 */

	/* 관리자 */
}
