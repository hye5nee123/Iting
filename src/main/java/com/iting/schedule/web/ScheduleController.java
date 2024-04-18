package com.iting.schedule.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	
	// 수강목록
	@RequestMapping("member/schedule/list")
	public String getScheduleList(Model model, LrnVO vo) {
		
		List<LrnVO> lists = lrnService.getLrnList();
		
		Map<String, Object> event = new HashMap<String, Object>();
		
		for(LrnVO list : lists) {
			String cntn = list.getScheCntn();
			event.put("strat", cntn);
		}
		
		// 내용 : 내용, 시작날짜: 날짜, 끝나는 날짜: 날짜
		
		/*
        Map<String, Object> event = new HashMap<String, Object>();
        
        List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
        
        event.put("start", lsit);
        event.put("title", "test");
        event.put("end",LocalDate.now());
        eventList.add(event);
        event = new HashMap<String, Object>();
        event.put("start", LocalDate.now().plusDays(3));
        event.put("title", "test2");
        event.put("end",LocalDate.now().plusDays(4));
        eventList.add(event);
        */
		
		
		
		model.addAttribute("list", lrnService.getLrnList());
		
		return "member/schedule/list";
	}
	
	
}
