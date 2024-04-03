package com.iting.cnq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iting.cnq.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	ReplyService replyService;
	

	
}
