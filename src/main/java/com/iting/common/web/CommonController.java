package com.iting.common.web;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iting.common.ExcelView;
import com.iting.common.model.AccountVO;
import com.iting.common.model.FileVO;
import com.iting.common.model.UsersVO;
import com.iting.common.service.CommonService;
import com.iting.common.service.UsersService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CommonController {
	
	@Autowired
	UsersService userservice;
	
	@Autowired
	CommonService commonService;
	
	/* 메인이동 */
	// 관리자
	@RequestMapping("admin/main")
	public ModelAndView goAdminMain() {
		ModelAndView mv  = new ModelAndView("admin/main");
		return mv;
	}
	
	// 회원
	@RequestMapping("member/main")
	public ModelAndView goMemberMain() {
		ModelAndView mv  = new ModelAndView("member/main");
		return mv;
	}
	
	// 강사
	@RequestMapping("teacher/main")
	public ModelAndView goTeacherMain() {
		ModelAndView mv  = new ModelAndView("teacher/main");
		return mv;
	}
	
	/* 파일 업로드 (싱글)*/
	@PostMapping("upload/file")
	@ResponseBody
	public int uploadFileTest(MultipartFile uFile, String fileCode) throws IllegalStateException, IOException {
		int retCode = 0; // 등록 완료 코드
		
		if(uFile != null) {
			// 원본 파일명
			String origFileName = uFile.getOriginalFilename();
			// 새 파일명 (중복 덮어쓰기 방지)
			String newFile = origFileName + "_" +  new Date().getSeconds();
			
			// 파일 생성 (저장 경로, 파일이름)
			File file = new File("D:/iting_webstorage/", uFile.getOriginalFilename());
			// 파일저장
			uFile.transferTo(file);
			
			
			System.out.println("파일명 : " + uFile.getOriginalFilename());
			System.out.println("파일크기 : " + uFile.getSize());
			
			// 첨부파일 DB에 저장하기
			FileVO fvo = FileVO.builder()
								.atchTtl(origFileName)
								.atchMarkTtl(newFile)
								// 파일 유형 코드 : 쿼리스트링으로 받아와서 넘겨야할지..										
								.atchTypCd(fileCode)
								// 파일 경로 구분 할 경우 파일 경로 들어가야 함.
								//.atchPath(filePath)
								.build();
					
			retCode = commonService.fileInsert(fvo);
		}
		return retCode;
		
	}
	
	/* 파일업로드 메소드 */
	public int uploadFile(MultipartFile uFile) throws IllegalStateException, IOException {
		int retCode = 0; // 등록 완료 코드
		
		if(uFile != null) {
			// 원본 파일명
			String origFileName = uFile.getOriginalFilename();
			// 새 파일명 (중복 덮어쓰기 방지)
			String newFile = origFileName + "_" +  new Date().getSeconds();
			
			// 파일 생성 (저장 경로, 파일이름)
			File file = new File("D:/iting_webstorage/", uFile.getOriginalFilename());
			// 파일저장
			uFile.transferTo(file);
			
			
			System.out.println("파일명 : " + uFile.getOriginalFilename());
			System.out.println("파일크기 : " + uFile.getSize());
			
			// 첨부파일 DB에 저장하기
			FileVO fvo = FileVO.builder()
								.atchTtl(origFileName)
								.atchMarkTtl(newFile)
								// 파일 경로 구분 할 경우 파일 경로 들어가야 함.
								//.atchPath(filePath)
								.build();
					
			retCode = commonService.fileInsert(fvo);
		}
		return retCode;
		
	}
	
	/* 파일 업로드 (멀티) */
	
	
	/* 파일 다운로드 */
	@GetMapping("/downfile")
	public ModelAndView downLoadFile(String fileName) {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	
	
	/* 엑셀로 DB 테이블 내려받기 */
	@GetMapping("/empExcel")
	public ModelAndView empExcel() {
		ModelAndView mv = new ModelAndView(new ExcelView());
		/*
		mv.addObject("type", EmpVO.class);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map> list = empService.getEmpList(null)
                                   .stream()
                                   .map(d->objectMapper.convertValue(d, Map.class))
                                   .collect(Collectors.toList());
	
		mv.addObject("datas", list);
		*/
		return mv;
	}
	
	
	// 페이지 권한 없을때
	@GetMapping("/accessError")
	public String accessDenied(Authentication auth, Model model) {
		log.info("access denied :" + auth);
		model.addAttribute("msg", "access denied");
		return "common/accessError";
	}
	
	/* 로그인 및 로그아웃 */
	
	// 로그인 페이지 이동
	@GetMapping("/login")
	public String loginForm() {
		return "common/login";
	}
	// 회원가입 페이지 이동
	@GetMapping("/account")
	public String accountForm() {
		return "common/account";
	}
	// id 중복확인
	@ResponseBody
	@GetMapping("/common/idchk/{id}")
	public UsersVO idchk(@PathVariable String id) {
		return userservice.getUserInfo(id);
	}
	@GetMapping("/account-email")
	public String emailsend() {
		return "common/account-email";
	}
	// 회원가입 등록
	@ResponseBody
	@PostMapping("/insertaccount")
	public int insertAccount(@RequestBody AccountVO vo) {
		System.out.println(vo);
		int ckcnt = userservice.insertUser(vo);
		return ckcnt;
	}
}