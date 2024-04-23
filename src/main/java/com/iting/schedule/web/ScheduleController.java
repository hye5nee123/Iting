package com.iting.schedule.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iting.member.model.MemberVO;
import com.iting.note.model.NoteVO;
import com.iting.schedule.model.LrnVO;
import com.iting.schedule.service.impl.LrnService;

/**
 * 
 * @author 신수지, 장효은
 * 
 */
@Controller
public class ScheduleController {
	
	@Autowired
	LrnService lrnService;

	
	@ResponseBody
	@PostMapping("member/schedule/insert")
	public LrnVO lrnInsert(@RequestBody LrnVO vo) {
		System.out.println(vo);
		 lrnService.lrnInsert(vo);
		 return vo;
	}
	
	// 수강목록
	@RequestMapping("member/schedule/list")
	public String getScheduleList(Model model, LrnVO vo) {
		
		List<LrnVO> lists = lrnService.getLrnList();
		
		List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
		Map<String, Object> event = new HashMap<String, Object>();
		
		for(LrnVO list : lists) {
			String cntn = list.getScheCntn();
			Date frDt = list.getFrDt();
			Date toDt = list.getToDt();
			event.put("start", frDt);
			event.put("title", cntn);
			event.put("end", toDt);
			eventList.add(event);
			event = new HashMap<String, Object>();
		}
		
		System.out.println(eventList.toString());

		model.addAttribute("eventList", eventList);
		
		return "member/schedule/list";
	}
	
	
}
