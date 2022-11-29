package com.joeun.joeunmall.dao;

import java.util.List;

import com.joeun.joeunmall.vo.ProductImageVO;
import com.joeun.joeunmall.vo.ProductOptionVO;
import com.joeun.joeunmall.vo.ProductVO;

public interface ProductDAO {

	/**
	 * 상품 정보 조회 (메인 갤러리-8개씩 2*4)
	 * 
	 * @param page 현재 페이지 
	 * @param recordsPerPage 페이지당 출력 상품 수
	 * @return 출력 상품 정보 
	 */
	public List<ProductVO> selectProductsByPaging(int currentPage, int recordsPerPage);

	/**
	 * 총 상품 개수
	 * 
	 * @return 총 상품 개수
	 */
	public int selectProductCount();
	
	/**
	 * 카테고리별 상품 정보 조회 (메인 갤러리-8개씩 2*4)
	 * 
	 * @param page 현재 페이지 
	 * @param recordsPerPage 페이지당 출력 상품 수
	 * @param productCategoryIndex 카테고리 번호 ex) 01
	 * @return 카테고리별 상품
	 */
	public List<ProductVO> selectProductsByPagingAndCategory(int currentPage, int recordsPerPage, String productCategoryIndex);

	/**
	 * 카테고리별 상품 수
	 * 
	 * @param productCategoryIndex 카테고리 번호 ex) 01
	 * @return 카테고리별 상품 수
	 */
	public int selectProductsCountByCategory(String productCategoryIndex);

	//상품상세페이지에서 사용
	/**
	 * 상품번호로 상품정보 조회
	 * 
	 * @param productIndex 상품번호
	 * @return 상품정보VO
	 */
	public ProductVO selectProductInfo(String productIndex);
	
	/**
	 * 상품번호로 상품이미지 조회
	 * 
	 * @param productIndex 상품번호
	 * @return 상품이미지VO
	 */
	public List<ProductImageVO> selectProductImage(String productIndex);
	
	/**
	 * 상품번호로 상품옵션 조회
	 * 
	 * @param productIndex 상품번호
	 * @return 상품옵션VO
	 */
	public List<ProductOptionVO> selectProductOption(String productIndex);
	
}
