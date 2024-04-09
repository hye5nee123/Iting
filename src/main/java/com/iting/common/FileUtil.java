package com.iting.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.iting.common.model.FileVO;

public class FileUtil {
	
	/* 파일업로드 메소드 */
	public static FileVO uploadFile(MultipartFile uFile) throws IllegalStateException, IOException {
		
		FileVO fvo = null;
		
		if(uFile != null) {
			// 원본 파일명
			String origFileName = uFile.getOriginalFilename();
			// 새 파일명 (중복 덮어쓰기 방지) => 수정필요(240409)
			String newFile = origFileName.substring(0, origFileName.lastIndexOf('.')) + "_" +  new Date().getSeconds();
			
			// 파일 생성 (저장 경로, 파일이름)
			File file = new File("D:/iting_webstorage/", uFile.getOriginalFilename());
			// 파일저장
			uFile.transferTo(file);
			
			
			System.out.println("파일명 : " + uFile.getOriginalFilename());
			System.out.println("파일크기 : " + uFile.getSize());
			
			// 첨부파일 DB에 저장하기
			fvo = FileVO.builder()
								.atchTtl(origFileName)
								.atchMarkTtl(newFile)
								// 파일 경로 구분 할 경우 파일 경로 들어가야 함.
								//.atchPath(filePath)
								.build();
					
		}
		return fvo;
		
	}
}
