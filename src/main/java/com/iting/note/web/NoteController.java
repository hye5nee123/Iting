package com.iting.note.web;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.iting.lecture.model.LectureVO;
import com.iting.lecture.service.LectureService;
import com.iting.member.model.MemberVO;
import com.iting.member.service.MemberService;
import com.iting.note.model.NoteVO;
import com.iting.note.service.NoteService;
import com.iting.socket.model.Greeting;
import com.iting.socket.model.HelloMessage;
import com.iting.teacher.service.TeacherService;
import com.iting.tlsn.service.TlsnService;

@Controller
public class NoteController {
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	NoteService noteService;

	@Autowired
	MemberService memberService;

	@Autowired
	TlsnService tlsnService;
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	LectureService lectureService;

	@Autowired
	private HttpSession httpSession;

	// 강사 - 회원 및 쪽지 목록조회
	@RequestMapping("teacher/note/list/{ltNum}")
	public String getNoteList(@PathVariable String ltNum, Model model, NoteVO vo) {
		String user = (String) httpSession.getAttribute("usernum");
		System.out.println(user);
		vo.setLtNum(ltNum);
		vo.setUser(user);
		MemberVO mvo = new MemberVO();
		mvo.setLtNum(ltNum);
		model.addAttribute("recList", noteService.getRecList(vo));
		model.addAttribute("sentList", noteService.getSentList(vo));
		model.addAttribute("memList", memberService.getMemberLtsn(mvo));
		return "teacher/note/list";
	}

	// 회원 - 강의 및 쪽지 목록조회
	@RequestMapping("member/note/list/{ltNum}")
	public String getMemNoteList(@PathVariable String ltNum, Model model, NoteVO vo) {
		String user = (String) httpSession.getAttribute("usernum");
		System.out.println(user);
		vo.setLtNum(ltNum);
		vo.setUser(user);
		model.addAttribute("recList", noteService.getRecList(vo));
		model.addAttribute("sentList", noteService.getSentList(vo));
		model.addAttribute("tc", teacherService.getTeacherInfo(ltNum));
		return "member/note/list";
	}

	// 강사 - 회원 단건조회
	@GetMapping("teacher/note/info/{noteNum}/{gb}")
	public String info(@PathVariable String noteNum, @PathVariable String gb, Model model, LectureVO vo) {
		model.addAttribute("note", noteService.getRecInfo(noteNum));
		model.addAttribute("gb", gb);
		return "teacher/note/info";
	}

	// 회원 - 강사 단건조회
	@GetMapping("member/note/info/{noteNum}/{gb}")
	public String infoMem(@PathVariable String noteNum, @PathVariable String gb, Model model) {
		model.addAttribute("note", noteService.getRecInfo(noteNum));
		model.addAttribute("gb", gb);
		return "member/note/info";
	}

	// 강사 - 회원 메세지 등록페이지 이동
	@GetMapping("teacher/note/insert/{ltNum}/{memNum}")
	public ModelAndView list(@PathVariable String ltNum, @PathVariable String memNum, Model model) {
		model.addAttribute("ltNum", ltNum);
		model.addAttribute("memNum", memNum);
		ModelAndView mv = new ModelAndView("teacher/note/insert");
		return mv;
	}

	// 메세지 등록
	@ResponseBody
	@PostMapping("teacher/note/insert")
	public NoteVO insertTest(@RequestBody NoteVO vo) {
		noteService.insertNote(vo);
		MemberVO mvo = memberService.getMemberInfo(vo.getRecPs());
		System.out.println(mvo);
		// 요청 처리 메세지를 보내고
		this.template.convertAndSendToUser(mvo.getId(), "/topic/message", vo);
		return vo;
	}

	// 회원 등록페이지 이동
	@GetMapping("member/note/insert/{ltNum}")
	public ModelAndView listMem(@PathVariable String ltNum, Model model) {
		model.addAttribute("tc", teacherService.getTeacherInfo(ltNum));
		model.addAttribute("ltNum", ltNum);
		ModelAndView mv = new ModelAndView("member/note/insert");
		return mv;
	}

	// 회원 메세지 등록
	@ResponseBody
	@PostMapping("member/note/insert")
	public NoteVO insertMemTest(@RequestBody NoteVO vo) {
		noteService.insertNote(vo);
		MemberVO mvo = memberService.getMemberInfo(vo.getRecPs());
		System.out.println(mvo);
		// 요청 처리 메세지를 보내고
		this.template.convertAndSendToUser(mvo.getId(), "/topic/message", vo);
		return vo;
	}

	@ResponseBody
	@GetMapping("test")
	public String test() {
		this.template.convertAndSendToUser("lect02", "/topic/message", "use 메세지가 도착했습니다.");
		this.template.convertAndSend( "/topic/message", "전페  ㅇ메세지가 도착했습니다.");
		return "test";
	}
}
