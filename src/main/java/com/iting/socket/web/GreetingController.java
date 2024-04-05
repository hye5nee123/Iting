package com.iting.socket.web;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.iting.socket.model.Greeting;
import com.iting.socket.model.HelloMessage;

@Controller
public class GreetingController {

  private SimpMessagingTemplate template;
	
  @MessageMapping("/hello") // 메세지가 들어오면
  @SendTo("/topic/greetings") // greetings 구독자에게 메세지 전송
  public Greeting greeting(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay // 1초 대기
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"); // 이 내용을 전송하겠다
  }
  
  @GetMapping("insert")
  @ResponseBody
  public String insert(String greeting) {
	// 서비스
	
	// 요청 처리 메세지를 보내고
    String text = "[" + new Date() + "]:" + "승인요청 ";
    this.template.convertAndSend("/topic/approve", text);
	return "ok";  
  }
}
