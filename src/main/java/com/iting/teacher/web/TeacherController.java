package com.iting.teacher.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.iting.teacher.service.TeacherService;

@Controller
public class TeacherController {

	@Autowired
	TeacherService teacherService;
}
