package com.iting.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.notice.model.NoticeVO;
import com.iting.notice.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	NoticeService noticeService;
	
	// 공지전체조회(관리자)
		@RequestMapping("/admin/notice/noticeList")
		public String noticeList(Model model, NoticeVO vo) {
			model.addAttribute("noticeList", noticeService.getNoticeList(vo));
			return "admin/notice/list";

		}
		
		// 공지전체조회(회원)..
		@RequestMapping("/member/notice/noticeList")
		public String noticeMemList(Model model, NoticeVO vo) {
			model.addAttribute("noticeMemList", noticeService.getNoticeList(vo));
			return "member/notice/list";

		}
}
