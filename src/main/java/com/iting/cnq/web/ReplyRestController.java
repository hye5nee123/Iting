package com.iting.cnq.web;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iting.cnq.model.CSearchVO;
import com.iting.cnq.model.CnqVO;
import com.iting.cnq.service.ReplyService;
import com.iting.common.Paging;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyRestController {
	final ReplyService replyService;

	// 댓글목록.
	@GetMapping("/member/cnq/rList")
	public Map<String, Object> replyList(CnqVO vo, CSearchVO svo, Paging pvo) {

		// 페이징처리
		pvo.setPageUnit(5); // 데이터수
		pvo.setPageSize(3); // 페이지번호
		svo.setStart(pvo.getFirst());
		svo.setEnd(pvo.getLast());

		Map<String, Object> map = replyService.getList(vo, svo);

		pvo.setTotalRecord((long) map.get("count"));
		map.put("paging", pvo);

		return map;

	}
}
