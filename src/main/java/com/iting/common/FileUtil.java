package com.iting.common;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.iting.common.model.FileVO;
import com.iting.common.service.CommonService;

@Component
public final class FileUtil {
	
	@Autowired
	CommonService commonService;
	
	
	private static String filePath;

	@Value("${file-upload-folder}")
    public void setFilePath(String filePath) {
		FileUtil.filePath = filePath;
    }
	
	/* 파일업로드 메소드 */
	public static FileVO uploadFile(MultipartFile uFile)  {
		
		// String uploadDir = "D:/iting_webstorage/";
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + "/"+filePath);
		
		// 디렉터리 생성
		File uploadPath = new File("/"+filePath);
		
		if (!uploadPath.exists()) {			
			uploadPath.mkdir();
		}
		
		String uuid = UUID.randomUUID().toString();
		
		FileVO fvo = null;
		
		if(uFile != null) {
			// 원본 파일명
			String origFileName = uFile.getOriginalFilename();
			
			// 확장자
			String exetension = FilenameUtils.getExtension(origFileName);
			
			// 새 파일명 (중복 덮어쓰기 방지)
			String newFile = origFileName.substring(0, origFileName.lastIndexOf('.')) + "_" +  uuid + "." +  exetension;
			
			
			// 파일 생성 (저장 경로, 파일이름)
			File file = new File("/"+filePath, newFile);
			// 파일저장
			try {
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
			} catch (Exception e) {
				e.printStackTrace();
			} 
					
		}
		return fvo;
		
	}
	
	/* 다운로드 미디어타입 세팅 메소드 */
	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String filename) {
        
        String minType = servletContext.getMimeType(filename);
        
        try {
            MediaType mediaType = MediaType.parseMediaType(minType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }

    }

}
