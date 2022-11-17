package com.joeun.joeunmall.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDTO {

	/** 상품명 */
	private String productName;
	
	/** 상품번호 */
	private String productIndex;
	
	/** 카테고리 번호 */
	private String productCategoryIndex;
	
	/** 판매상태정보 */
	private String productStateInfo;
	
	/** 상품가격 */
	private int productPrice;
	
	/** 상품 대표 이미지 */
	private MultipartFile uploadFile1;
	
	/** 상품 대표 이미지 파일명 */
	private String updateFile1Filename;
	
	/** 상품 이미지들 */
	private MultipartFile uploadFiles1;
	private MultipartFile uploadFiles2;
	private MultipartFile uploadFiles3;
	private MultipartFile uploadFiles4;
	private MultipartFile uploadFiles5;
	
	/** 이미지 상세정보*/
	private String productInfo;
	
	/** 상품옵션 */
	private String productOption1;
	private String productOption2;
	private String productOption3;
	private String productOption4;
	private String productOption5;
}
