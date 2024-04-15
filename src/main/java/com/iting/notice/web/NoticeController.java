package com.iting.notice.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iting.cnq.model.CSearchVO;
import com.iting.common.model.PagingVO;
import com.iting.notice.model.NoticeVO;
import com.iting.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NoticeController {
	@Autowired
	NoticeService noticeService;
	
	// 공지전체조회. //
		@RequestMapping("/admin/notice/noticeList")
		public String noticeList(Model model, NoticeVO vo, CSearchVO svo, PagingVO pvo) {
			// paging처리.
			pvo.setPageUnit(5); // 데이터수
			pvo.setPageSize(3); // 페이지번호
			svo.setStart(pvo.getFirst());
			svo.setEnd(pvo.getLast());
			System.out.println(">>>>>>>>>>>> " + vo + ":" + svo);
			Map<String, Object> map = noticeService.getNoticeList(vo, svo);
			pvo.setTotalRecord((long) map.get("count"));

			model.addAttribute("paging", pvo);
			model.addAttribute("noticeList", map.get("data"));
			return "admin/notice/list";

		}
}
