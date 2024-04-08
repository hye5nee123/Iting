package com.iting.lecture.web;

import java.util.List;

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

import com.iting.common.model.PagingVO;
import com.iting.lecture.model.LectureVO;
import com.iting.lecture.service.LectureService;

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
	public List<LectureVO> allList(LectureVO vo, PagingVO pvo) {
		pvo.setPageUnit(16);
		pvo.setPageSize(16);
		pvo.setFirst(0);
		pvo.setLast(5);
		
		List<LectureVO> list = lectureService.getAllLectureList(vo, pvo);
		
		return list;
		
	}

	// 강의 단건 조회
	@GetMapping("lecture/info/{ltNum}")
	public String info(@PathVariable String ltNum, Model model) {
		model.addAttribute("lecture", lectureService.getLectureInfo(ltNum));
		return "member/lecture/info";
	}

  
	/* 강사 */
	// 강의 목록 조회
	@GetMapping("lecture/lecture")
	public String getLectureList(Model model, LectureVO vo) {
		model.addAttribute("getLectureList", lectureService.getLectureList(vo));
		return "teacher/lecture/getLectureList";
	}

	// 강의 단건 조회
	@GetMapping("lecture/info1/{ltNum}")
	public String info1(@PathVariable String ltNum, Model model) {
		model.addAttribute("lecture", lectureService.getLectureInfo1(ltNum));
		return "teacher/lecture/info1";
	}
	// 강의 수정

	// 강의 등록페이지 이동
	@GetMapping("/teacher/lecture/insert")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("teacher/lecture/insert");
		return mv;
	}

	// 등록기능
	@ResponseBody
	@PostMapping("/teacher/lecture/insert1")
	public String ltInsert(@RequestBody LectureVO vo) {
		System.out.println(vo +
				"===============");
		lectureService.ltInsert(vo);
		return "redirect:/admin/lecture/list";
	}
	/* 관리자 */
	// 강의 수정

	//강의 리스트
	@GetMapping("/admin/lecture/list")
	public String list(Model model, LectureVO vo) {
		model.addAttribute("list", lectureService.getLectureList(vo));
		return "/admin/lecture/list";
	}
	
	
}