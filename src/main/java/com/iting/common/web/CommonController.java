package com.iting.common.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
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

	@Autowired
	HttpSession httpSession;

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

	/* 첨부 파일 업로드 - 테스트용 */
	@RequestMapping("upload/fileTest")
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

	/* 첨부 파일 업로드 (싱글) */
	@PostMapping("upload/file")
	@ResponseBody
	public Map<String, Object> uploadFileTest(MultipartFile uFile, String fileCode)
			throws IllegalStateException, IOException {
		int retCode = 0; // 등록 완료 코드

		Map<String, Object> map = new HashMap<String, Object>();

		FileVO fvo = FileUtil.uploadFile(uFile);
		if (fvo != null) {
			// 달라 질수 있는 로직
			retCode = commonService.fileInsert(fvo);

			map.put("retCode", retCode);
			map.put("fvo", fvo);
		}
		return map;

	}

	/* 첨부 파일 업로드 (멀티) */
	@PostMapping("upload/files")
	@ResponseBody
	public List<Map<String, Object>> uploadFileTest(MultipartFile[] uFile, String fileCode)
			throws IllegalStateException, IOException {
		int retCode = 0; // 등록 완료 코드

		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		if (uFile != null) {
			for (MultipartFile file : uFile) {

				if (!file.isEmpty()) {
					Map<String, Object> map = new HashMap<String, Object>();

					FileVO fvo = FileUtil.uploadFile(file);
					retCode = commonService.fileInsert(fvo);

					map.put("retCode", retCode);
					map.put("fvo", fvo);

					listMap.add(map);
				}
			}
		}

		return listMap;

	}

	// 첨부파일을 클릭하면 -> 첨부파일번호 단건조회 -> 파일 다운로드 받기 -> (싱글 파일 된 후) 다중파일은 zip파일로 변환후 받을 수
	// 있게 해보잡!
	/* 첨부 파일 다운로드 */
	@GetMapping("/downloading/{fileNum}")
	@ResponseBody
	public FileVO findFile(@PathVariable String fileNum) {
		return commonService.getFileInfo(fileNum);
	}

	/* 첨부 파일 다운로드 - 테스트용 */
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
	public void accessDenied(Authentication auth, Model model) {
		log.info("access denied :" + auth);
		model.addAttribute("msg", "Access denied");
	}

	/* 로그인 및 로그아웃 */

	// 로그인 페이지 이동
	@GetMapping("/commonlogin")
	public String loginForm() {
		return "common/login";
	}

	@GetMapping("/naveraccount")
	public String naverAccForm() {
		return "common/naverAccount";
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

	// 회원 등록
	@ResponseBody
	@PostMapping("/insertLecturer")
	public int insertLecturer(@RequestPart(value = "vo", required = false) AccountVO vo,
			@RequestPart(value = "fileData", required = false) MultipartFile fileData)
			throws IllegalStateException, IOException {
			System.out.println(fileData.getOriginalFilename());
			String origFileName = fileData.getOriginalFilename();
			vo.setFileName(fileData.getOriginalFilename());
			// 확장자
			String exetension = FilenameUtils.getExtension(origFileName);
			// 새 파일명 (중복 덮어쓰기 방지)
			String newFile = origFileName.substring(0, origFileName.lastIndexOf('.')) + "_" + new Date().getSeconds()
					+ "." + exetension;
			vo.setNewFileName(newFile);
			// 파일 경로 및 이름 정의
			File file = new File("D:/iting_webstorage/", fileData.getOriginalFilename());
			// 정의된 값으로 파일 저장
			try {
				fileData.transferTo(file);
				System.out.println("파일명 : " + fileData.getOriginalFilename());
				System.out.println("파일크기 : " + fileData.getSize());
			} catch (Exception e) {
				e.printStackTrace();
			}
		int ckcnt = userservice.insertUser(vo);
		return ckcnt;
	}
	@ResponseBody
	@PostMapping("/insertMember")
	public int insertMember(@RequestBody AccountVO vo) {
		int ckcnt = userservice.insertUser(vo);
		return ckcnt;
	}
	
	// 회원 메인
	@GetMapping("/")
	public String index() {
		return "member/main";
	}
}