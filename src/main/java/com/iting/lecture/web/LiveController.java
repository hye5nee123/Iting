package com.iting.lecture.web;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iting.common.FileUtil;
import com.iting.common.model.FileVO;
import com.iting.common.model.PagingVO;
import com.iting.lecture.model.LiveVO;
import com.iting.lecture.service.LiveService;

@Controller
public class LiveController {

	@Autowired
	LiveService liveService;

	//목록 조회
	@GetMapping("/teacher/live/getLiveList")
	public String list(Model model, LiveVO vo) {
		model.addAttribute("getLiveList", liveService.getLiveList(vo));
		return "teacher/live/getLiveList";
	}
	
	//등록페이지 이동
	@GetMapping("/teacher/live/insert")
	public void insertForm() {
		
	}
	
	//등록기능
	@ResponseBody
	@PostMapping("/teacher/live/insert")
	public String spltInsert(@RequestBody LiveVO vo) {
		liveService.spltInsert(vo);
		return "redirect:/teacher/live/getLiveList";
	}
	
	
	/* 괸리자 */
	
	//미승인 목록
	@GetMapping("/admin/live/ingLiveList")
	public String ingLiveList(Model model, String spltNum, LiveVO vo) {
		model.addAttribute("ingLiveList", liveService.ingLiveList(vo));
		return "/admin/live/ingLiveList";
	}
	
	@RequestMapping("/admin/live/allendLiveList")
	public ModelAndView allLiveList() {
		ModelAndView mv = new ModelAndView("/admin/live/endLiveList");
		return mv;
	} 
	
	//승인 완료 목록
	@ResponseBody
	@GetMapping("/admin/live/endLiveList")
	public Map<String, Object>endLiveList(LiveVO vo, PagingVO pvo){
		pvo.setPageUnit(5);
		pvo.setPageSize(5);
		pvo.setFirst(0);
		pvo.setLast(5);
		Map<String, Object> map = new HashMap<String, Object>();
		pvo.setTotalRecord(liveService.getCount(vo));
		
		List<LiveVO> list = liveService.endLiveList(vo, pvo);
		
		map.put("data", list);
		map.put("paging", pvo);

		return map;
	}
	
	//승인수정 기능
	@ResponseBody
	@GetMapping("/admin/live/update/{spltNum}/{accpYnCd}")
	public LiveVO update(LiveVO vo, @PathVariable String spltNum, @PathVariable String accpYnCd) {
		vo.setSpltNum(spltNum);
		vo.setAccpYnCd(accpYnCd);
		liveService.update(vo);
		return vo;
	}
	
}
	
