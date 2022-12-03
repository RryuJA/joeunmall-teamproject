package com.joeun.joeunmall.service;

import org.springframework.web.multipart.MultipartFile;

import com.joeun.joeunmall.vo.ProductDTO;

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
	 * 파일 업로드 서비스 (수정용)
	 * 
	 * @param file 업로드할 파일
	 * @param filename 업로드할 파일 이름 ex) 01_tshirt/22_01_002_thumbnail.jpg
	 * @param catePath 이미지 카테고리 경로 ex) 01_tshirt
	 * @return 업로드 결과 메시지
	 */
	public String storeUploadFile(MultipartFile file, String filename, String catePath);
	
	/**
	 * 파일 삭제 서비스 (수정용)
	 * 
	 * @param filename 삭제할 파일 이름 ex) 01_tshirt/22_01_002_thumbnail.jpg
	 * @return 삭제 결과 메시지
	 */
	public String deleteUploadFile(String filename);
	
	/**
	 * 업로드 파일명 구성 ex) 01_tshirt/22_01_002_thumbnail.jpg
	 * 
	 * @param productDTO 상품 전송 객체 
	 * @param isThumbnail 대표 이미지 여부 ex) 대표이미지 >> true
	 * @param index 상품 이미지 번호 ex) 1
	 * @return 실제 업로드 파일명 
	 */
	public String setUploadFileName(ProductDTO productDTO, boolean isThumbnail,int index);
	
	/**
	 * 업로드 유효 파일 점검
	 *  
	 * @param file 업로드할 파일
	 * @return 업로드 가능 파일여부
	 */
	public boolean isValidUploadFile(MultipartFile file);   
}
