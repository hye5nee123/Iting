package com.iting.note.web;

import java.util.Date;

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

import com.iting.note.model.NoteVO;
import com.iting.note.service.NoteService;
import com.iting.socket.model.Greeting;
import com.iting.socket.model.HelloMessage;

@Controller
public class NoteController {
	
	private SimpMessagingTemplate template;
	
	@Autowired
	NoteService noteService;
	
	// 목록조회
	@RequestMapping("teacher/note/list")
	public String getNoteList(Model model, NoteVO vo) {
		model.addAttribute("noteList", noteService.getNoteList());
		return "teacher/note/list";
	}
	
	// 단건조회
	@GetMapping("teacher/note/info/{noteNum}")
	public String info(@PathVariable String noteNum, Model model) {
		model.addAttribute("note", noteService.getNoteInfo(noteNum));
		return "teacher/note/info";
	}
	
	@MessageMapping("/hello") // 메세지가 들어오면
	  @SendTo("/topic/greetings") // greetings 구독자에게 메세지 전송
	  public Greeting greeting(HelloMessage message) throws Exception {
	    Thread.sleep(1000); // simulated delay // 1초 대기
	    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"); // 이 내용을 전송하겠다
	  }
	
	// 등록페이지 이동
	@GetMapping("teacher/note/insert/{noteNum}")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("teacher/note/insert");
		return mv;
	}
	
	// 메세지 등록
	@ResponseBody
	@PostMapping("teacher/note/insert")
	public NoteVO insertTest(@RequestBody NoteVO vo) {
		noteService.insertNote(vo);
		
	// 요청 처리 메세지를 보내고
    String text = "[" + new Date() + "]:" + "승인요청 ";
    this.template.convertAndSendToUser(
    	vo.getRecPs(), "/topic/message", noteService.insertNote(vo));
	return vo;  
  }
	
}
