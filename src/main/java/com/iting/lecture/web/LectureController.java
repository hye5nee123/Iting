package com.iting.lecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iting.common.FileUtil;
import com.iting.common.model.FileVO;
import com.iting.common.model.PagingVO;
import com.iting.lecture.model.LectureVO;
import com.iting.lecture.service.LectureService;

/**
 * 
 * @author 장효은,도승민
 * 강의관리(회원,강사,관리자)
 * 
 */
@Controller
public class LectureController {
	
	@Autowired
	LectureService lectureService;

	/* 회원 */
	// 강의 전체목록 페이지 이동
	@RequestMapping("/member/lecture/allList")
	public ModelAndView allList() {
		ModelAndView mv = new ModelAndView("/member/lecture/allList");
		return mv;
	}

	// 강의 전체목록 조회처리 - 장효은
	@GetMapping("/member/lecture/allSelect")
	@ResponseBody
	public Map<String, Object> allList(LectureVO vo, PagingVO pvo) {
		pvo.setPageUnit(16);
		pvo.setPageSize(16);
		pvo.setFirst(0);
		pvo.setLast(5);

		Map<String, Object> map = new HashMap<String, Object>();

		List<LectureVO> list = lectureService.getAllLectureList(vo, pvo);

		map.put("lectList", list);
		map.put("paging", pvo);

		return map;

	}

	
	// 강의 단건 조회
	@GetMapping("member/lecture/info/{ltNum}")
	public String info(@PathVariable String ltNum, LectureVO vo, Model model) {
		model.addAttribute("lecture", lectureService.getLectureInfo(ltNum));
		model.addAttribute("curriList", lectureService.getCurriList(vo));
		model.addAttribute("cur", lectureService.getCurriAll(ltNum));
		return "member/lecture/info";
	}

	/* 강사  -도승민*/
	// 강의 목록 조회
	@GetMapping("/teacher/lecture/lecture")
	public String getLectureList(Model model, LectureVO vo, HttpSession session) {
		vo.setLecturerNum((String)session.getAttribute("usernum"));
		model.addAttribute("getLectureList", lectureService.getLectureList(vo));
		return "teacher/lecture/getLectureList";
	}

	// 강의 단건 조회
	@GetMapping("/teacher/lecture/info/{ltNum}")
	public String info1(@PathVariable String ltNum, Model model) {
		model.addAttribute("lecture", lectureService.getLectureInfo1(ltNum));
		return "teacher/lecture/info";
	}
	// 강의 수정

	// 강의 등록페이지 이동
	@GetMapping("/teacher/lecture/insert")
	public void insertForm() {	}

	// 등록기능
	@ResponseBody
	@PostMapping("/teacher/lecture/insert")
	public String ltInsert(@RequestBody LectureVO vo, MultipartFile uFile) {
		System.out.println(vo + "===============");
		FileVO fvo = FileUtil.uploadFile(uFile);
		if(fvo != null ) {
			vo.setAtchNum(fvo.getAtchNum());
		}
		lectureService.ltInsert(vo);
		return "redirect:/teacher/lecture/list";
	}
	
	/* 관리자 */
	// 강의 수정

	
	// 강의 리스트
	@GetMapping("/admin/lecture/list")
	public String list(Model model, LectureVO vo) {
		model.addAttribute("list", lectureService.getLectureList(vo));
		return "/admin/lecture/list";
	}

	//승인 대기 목록	
	@GetMapping("/admin/lecture/ingLectureList")
	public String ingLectureList(Model model, LectureVO vo) {
		model.addAttribute("ingLectureList", lectureService.ingLectureList(vo));
		return "/admin/lecture/ingLectureList";

	}
	
	//승인 완료 목록
	@GetMapping("/admin/lecture/endLectureList")
	public String endLectureList(Model model, LectureVO vo) {
		model.addAttribute("endLectureList", lectureService.endLectureList(vo));
		return "/admin/lecture/endLectureList";
	}

	// 승인수정 기능
	@ResponseBody
	@GetMapping("/admin/lecture/update/{ltNum}/{accpYnCd}")
	public LectureVO update(LectureVO vo, @PathVariable String ltNum, @PathVariable String accpYnCd) {
		vo.setLtNum(ltNum);
		vo.setAccpYnCd(accpYnCd);
		lectureService.update(vo);
		return vo;
	}

}