package com.joeun.joeunmall.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	/**
	 * 파일 업로드 서비스
	 * 
	 * @param file 업로드할 파일
	 * @param catePath 이미지 카테고리 경로 ex) 01_tshirt
	 * @return 업로드 결과 메시지
	 */
	public String storeUploadFile(MultipartFile file, String catePath);
	
	/**
	 * 업로드 유효 파일 점검
	 *  
	 * @param file 업로드할 파일
	 * @return 업로드 가능 파일여부
	 */
	public boolean isValidUploadFile(MultipartFile file);   
}
