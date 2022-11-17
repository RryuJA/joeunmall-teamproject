package com.joeun.joeunmall.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private FileSystemResource uploadDirResource;
	
	@Override
	public String storeUploadFile(MultipartFile file, String catePath) {
		
		String result = "";
		FileOutputStream fos = null;
		
		// 저장 경로 확보
	 	String savePath = uploadDirResource.getPath();
		
		log.info("##### 자원 경로(업로드) : " + savePath);
	 	
		// 업로드 파일 처리
	 	// 첨부 파일이 없을 때
		if (file.isEmpty() || file == null) {
			
			result = "첨부 파일이 없습니다.";
			log.error(result);
		
		} else { // 파일 유효성 점검
			
			// 저장 폴더 존재 점검
			Path path = Paths.get(savePath);
			 
			if (Files.exists(path)) {
				
				log.info("파일 업로드 저장소(폴더)가 존재합니다.");
				 
			} else {
				
				result = "파일 업로드 저장소(폴더)가 존재하지 않습니다.";
				log.error(result);
				 
				// 폴더 생성
				try {
					Files.createDirectory(path);	
				} catch (Exception e) {
					result = "파일 업로드 저장소(폴더)가 생성되지 않았습니다.";
					log.error(result);
				}
			} //
		   
		     
		    try {
	    	 	// 업로드 파일 형식 변환(시작) : 추가
		    	String filename = file.getOriginalFilename();
				log.info("실제 업로드 파일명 : {}", filename);
				// 업로드 파일 형식 변환(끝) : 추가
				// 기존파일 존재시에 삭제 후 신규 업로드 파일 저장
				
				if(Files.exists(Paths.get(savePath + "/" + catePath + "/" + file.getOriginalFilename()))== true) {
					Files.delete(Paths.get(savePath + "/" + catePath + "/" + file.getOriginalFilename()));
					log.info("기존 업로드 파일 삭제");
				}
				
	    	 	byte[] bytes = file.getBytes();
	    	 	
	    	 	log.info("### savePath : " + savePath);

	            File outFileName = new File(savePath + "/" + catePath + "/" + filename);
	            fos = new FileOutputStream(outFileName);
	             
	            fos.write(bytes);
	            
	            // 추가 : docker 런칭시 파일 권한 변경
	            log.info("파일 읽기 모드 점검 : " 
	            		+ Files.isReadable(Paths.get(savePath + "/" + catePath + "/" + filename)));
	            
	            result = "파일이 업로드 되었습니다.";	
	            
	            log.info("______________");
		         
		    } catch (IOException e) {
		    	 
		    	result = "파일 처리중 오류가 발생하였습니다. ";
		        log.error(result);
		        e.printStackTrace();
		         		        		 
		    } finally { // 자원 반납
		           
				try {    
				    if (fos!=null) fos.close();
				       
				} catch (IOException e) {
					result = "파일 처리중 오류가 발생하였습니다. ";
					log.error("FileUploadService storeUploadFile IOE : " + result);
				    e.printStackTrace();
				}
				 
		    } // try
		     
		} // 업로드 파일 처리
		
		return result;
	}

	@Override
	public boolean isValidUploadFile(MultipartFile file) {

		log.info("업로드 유효 파일 점검");
		boolean result = false;
		
		//파일객체 = null(선택되지 않은 파일)이거나 파일 이름 = 공백(선택되었지만 업로드 되지 않은 파일필드) >> 제외
		result = file == null ? false : file.getOriginalFilename().equals("") ? false : true;
		return result;
	}

}
