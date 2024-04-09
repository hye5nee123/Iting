package com.iting.common.web;

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
import com.iting.common.FileUtil;
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
		ModelAndView mv = new ModelAndView("admin/main");
		return mv;
	}

	// 회원
	@RequestMapping("member/main")
	public ModelAndView goMemberMain() {
		ModelAndView mv = new ModelAndView("member/main");
		return mv;
	}

	// 강사
	@RequestMapping("teacher/main")
	public ModelAndView goTeacherMain() {
		ModelAndView mv = new ModelAndView("teacher/main");
		return mv;
	}

	/* 파일 업로드 */
	@RequestMapping("upload/file")
	public String uploadFile(MultipartFile[] uploadFiles) throws IllegalStateException, IOException {
		if (uploadFiles != null) {
			for (MultipartFile uFile : uploadFiles) {
				if (!uFile.isEmpty()) { // if(photo.getSize > 0){}
					// 파일 이름이 중복일 경우 새 이름으로 저장 될 수 있도록
					String Origin = uFile.getOriginalFilename();
					// String newFile = Origin + new Date().getSeconds();

					// 파일 생성 (저장 경로, 파일이름)
					File file = new File("D:/iting_webstorage/", uFile.getOriginalFilename());
					// 파일저장
					uFile.transferTo(file);

					System.out.println("파일명 : " + uFile.getOriginalFilename());
					System.out.println("파일크기 : " + uFile.getSize());

					// 첨부파일 DB에 저장하기

				}
			}
		}
		return "/member/main";

	}
	
	
	/* 파일 업로드 (싱글)*/
	@PostMapping("upload/file")
	@ResponseBody
	public Map<String, Object> uploadFileTest(MultipartFile uFile, String fileCode) throws IllegalStateException, IOException {
		int retCode = 0; // 등록 완료 코드
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		FileVO fvo = FileUtil.uploadFile(uFile);
		if(fvo != null) {
			// 달라 질수 있는 로직
			retCode = commonService.fileInsert(fvo);
			
			map.put("retCode", retCode);
			map.put("fvo", fvo);
		}
		return map;
		
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
		 * mv.addObject("type", EmpVO.class); ObjectMapper objectMapper = new
		 * ObjectMapper(); List<Map> list = empService.getEmpList(null) .stream()
		 * .map(d->objectMapper.convertValue(d, Map.class))
		 * .collect(Collectors.toList());
		 * 
		 * mv.addObject("datas", list);
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

	@GetMapping("/send-email")
	public String emailsend() {
		return "common/send-email";
	}

	// 회원가입 등록
	@ResponseBody
	@PostMapping("/insertaccount")
	public int insertAccount(@RequestBody AccountVO vo) {
		System.out.println(vo);
		int ckcnt = userservice.insertUser(vo);
		return ckcnt;
	}
	
	// 네이버 로그인 이동
	@GetMapping("/naverlogin")
	public String naverlog() {
		return "common/naverlogin";
	}
}