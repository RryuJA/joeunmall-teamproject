package com.joeun.joeunmall.service;

import com.joeun.joeunmall.vo.ProductDTO;
import com.joeun.joeunmall.vo.ProductImageVO;

public interface ProductRegistrationService {

	/**
	 * 상품테이블에서 해당 카테고리 중 마지막 상품번호 조회
	 * 
	 * @param yearCate ex) 22_01
	 * @return 마지막 상품 번호 ex) 22_01_001
	 */
	public String selectMaxProductIndex(String yearCate);
	
	/** 
	 * 상품 정보 등록 
	 * 
	 * @param productDTO 상품 정보 객체
	 * @return 등록 성공 여부 
	 */
	public boolean insertProduct(ProductDTO productDTO);
	
	/**
	 * 상품 이미지 등록 
	 * 
	 * @param productImageVO 상품 정보 이미지 객체 
	 * @return 등록 성공 여부 
	 */
	public boolean insertProductImages(ProductImageVO productImageVO);
	
	/** 
	 * 상품 수정
	 * 
	 * @param productDTO 상품 정보 객체
	 * @return 등록 성공 여부 
	 */
	public boolean updateProduct(ProductDTO productDTO);
	
	/**
	 * 상품 이미지 수정 
	 * 
	 * @param productImageVO 상품 정보 이미지 객체 
	 * @return 등록 성공 여부 
	 */
	public boolean updateProductImages(ProductImageVO productImageVO);
	
	
	
	/**
	 * 상품 이미지 마지막 번호 조회
	 * 
	 * @param productIndex 상품번호
	 * @return 상품 이미지 마지막 번호 
	 */
	public String selectMaxProductImageIndex(String productIndex);
	
	/**
	 * 상품 카테고리 저장 경로 조회 
	 * 
	 * @param cateNum 카테고리 번호 ex) 01
	 * @return 카테고리 저장 경로 ex) 01_tshirt
	 */
	public String getCatePath(String cateNum);
	
	/**
	 * 상품 이미지 정보 조회
	 * 
	 * @param productImageIndex 상품 이미지 번호
	 * @return 상품 이미지
	 */
	public ProductImageVO selectProductImageByImageIndex(String productImageIndex);
	
	/**
	 * 상품 이미지 삭제
	 * 
	 * @param productImageIndex 상품 이미지 번호
	 * @return 삭제 여부
	 */
	public boolean deleteProductImages(String productImageIndex);
}
