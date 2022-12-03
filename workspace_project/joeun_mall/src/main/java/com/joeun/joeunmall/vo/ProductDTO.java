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
	
	/** 상품 이미지들 삭제 여부 */
	private String uploadImageDeleteYn1 = "N";
	private String uploadImageDeleteYn2 = "N";
	private String uploadImageDeleteYn3 = "N";
	private String uploadImageDeleteYn4 = "N";
	private String uploadImageDeleteYn5 = "N";
	
	/** 이미지 상세정보*/
	private String productInfo;
	
	/** 상품옵션 */
	private String productOption1;
	private String productOption2;
	private String productOption3;
	private String productOption4;
	private String productOption5;
	@Override
	public String toString() {
		return "ProductDTO [productName=" + productName + ", productIndex=" + productIndex + ", productCategoryIndex="
				+ productCategoryIndex + ", productStateInfo=" + productStateInfo + ", productPrice=" + productPrice
				+ ", uploadFile1=" + (uploadFile1==null || uploadFile1.isEmpty() ? "파일없음":uploadFile1.getOriginalFilename()) + ","
				+ " updateFile1Filename=" + updateFile1Filename + ","
				+ " uploadFiles1=" + (uploadFiles1==null || uploadFiles1.isEmpty() ? "파일없음":uploadFiles1.getOriginalFilename()) + ","
				+ " uploadFiles2=" + (uploadFiles2==null || uploadFiles2.isEmpty() ? "파일없음":uploadFiles2.getOriginalFilename()) + ","
				+ " uploadFiles3=" + (uploadFiles3==null || uploadFiles3.isEmpty() ? "파일없음":uploadFiles3.getOriginalFilename()) + ","
				+ " uploadFiles4=" + (uploadFiles4==null || uploadFiles4.isEmpty() ? "파일없음":uploadFiles4.getOriginalFilename()) + ","
				+ " uploadFiles5=" + (uploadFiles5==null || uploadFiles5.isEmpty() ? "파일없음":uploadFiles5.getOriginalFilename()) + "," 
				+ ", uploadImageDeleteYn1=" + uploadImageDeleteYn1
				+ ", uploadImageDeleteYn2=" + uploadImageDeleteYn2 + ", uploadImageDeleteYn3=" + uploadImageDeleteYn3
				+ ", uploadImageDeleteYn4=" + uploadImageDeleteYn4 + ", uploadImageDeleteYn5=" + uploadImageDeleteYn5
				+ ", productInfo=" + productInfo + ", productOption1=" + productOption1 + ", productOption2="
				+ productOption2 + ", productOption3=" + productOption3 + ", productOption4=" + productOption4
				+ ", productOption5=" + productOption5 + "]";
	}
	
	/*@Override
	public String toString() {
		
		return "ProductDTO [productName=" + productName + ", productIndex=" + productIndex + ", productCategoryIndex="
				+ productCategoryIndex + ", productStateInfo=" + productStateInfo + ", productPrice=" + productPrice
				+ ", uploadFile1=" + (uploadFile1==null || uploadFile1.isEmpty() ? "파일없음":uploadFile1.getOriginalFilename()) + ","
				+ " updateFile1Filename=" + updateFile1Filename + ","
				+ " uploadFiles1=" + (uploadFiles1==null || uploadFiles1.isEmpty() ? "파일없음":uploadFiles1.getOriginalFilename()) + ","
				+ " uploadFiles2=" + (uploadFiles2==null || uploadFiles2.isEmpty() ? "파일없음":uploadFiles2.getOriginalFilename()) + ","
				+ " uploadFiles3=" + (uploadFiles3==null || uploadFiles3.isEmpty() ? "파일없음":uploadFiles3.getOriginalFilename()) + ","
				+ " uploadFiles4=" + (uploadFiles4==null || uploadFiles4.isEmpty() ? "파일없음":uploadFiles4.getOriginalFilename()) + ","
				+ " uploadFiles5=" + (uploadFiles5==null || uploadFiles5.isEmpty() ? "파일없음":uploadFiles5.getOriginalFilename()) + ","
						+ " productInfo=" + productInfo + ", productOption1="
				+ productOption1 + ", productOption2=" + productOption2 + ", productOption3=" + productOption3
				+ ", productOption4=" + productOption4 + ", productOption5=" + productOption5 + "]";
	}*/
	
}
