package com.iting.save.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iting.cnq.model.CnqVO;
import com.iting.save.model.SaveListVO;
import com.iting.save.model.SaveVO;
import com.iting.save.service.SaveService;

import lombok.RequiredArgsConstructor;

/**
 * 강의에 대한 커뮤니티 사용.
 * 
 * @author 조혜원
 *
 */

@RequiredArgsConstructor
@Controller
public class SaveController {

	@Autowired
	SaveService saveService ;
	
		// 등록 기능.
		@ResponseBody
		@PostMapping("/member/save/insert")
		public int saveInsert(@RequestBody SaveVO vo) {
			return saveService.saveInsert(vo);
		}
		
		// 이름조회 기능.
		@ResponseBody
		@GetMapping("/member/save/insert/{id}")
		public String memSelect(@PathVariable String id) {
			return saveService.memSelect(id);
		}

		// 마이페이지 내가 찜한 목록 이동
		@GetMapping("/member/mypage/savelist")
		public String goMySave(Model model, HttpSession session) {
			String usernum = (String) session.getAttribute("usernum");
			List<SaveListVO> savelist = saveService.getSaveList(usernum);
			if(!savelist.isEmpty()) {
				model.addAttribute("savelist", savelist);
			}
			return "member/mypage/saveList";
		}
		
		// 내가 찜한 목록 삭제
		@ResponseBody
		@DeleteMapping("/member/savedelete")
		public int deleteSaves(@RequestBody List<String> ltNums, HttpSession session) {
			System.out.println(ltNums);
			String memNum = (String) session.getAttribute("usernum");
			System.out.println(memNum);
			return saveService.deleteSave(ltNums, memNum);
		}
		
}
