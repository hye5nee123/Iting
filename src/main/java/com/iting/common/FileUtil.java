package com.iting.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import com.iting.common.model.FileVO;
import com.iting.common.service.CommonService;

import okio.Path;

public class FileUtil {
	
	@Autowired
	CommonService commonService;
	
	/* 파일업로드 메소드 */
	public static FileVO uploadFile(MultipartFile uFile) throws IllegalStateException, IOException {
		
		FileVO fvo = null;
		
		if(uFile != null) {
			// 원본 파일명
			String origFileName = uFile.getOriginalFilename();
			
			// 확장자
			String exetension = FilenameUtils.getExtension(origFileName);
			
			// 새 파일명 (중복 덮어쓰기 방지) => 수정필요(240409)
			String newFile = origFileName.substring(0, origFileName.lastIndexOf('.')) + "_" +  new Date().getSeconds() + exetension;
			
			
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
	
	/* 첨부파일 다운로드 메소드 
	public Resource readFileResurce(FileVO vo) {
		
		vo.getAtchPath();
		String filename = vo.getAtchMarkTtl(); // 파일명
				
		Path filePath = Path.get(uloadpath); // 파일경로
		
		Resource resource = new UrlResource();
		
		return resource;
		
	}*/
	
	
	/*
	public Resource readFileAsResource(final FileResponse file) {
        String uploadedDate = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String filename = file.getSaveName();
        Path filePath = Paths.get(uploadPath, uploadedDate, filename);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() == false || resource.isFile() == false) {
                throw new RuntimeException("file not found : " + filePath.toString());
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException("file not found : " + filePath.toString());
        }
    }
	 
	*/
}
