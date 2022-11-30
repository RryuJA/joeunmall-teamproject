package com.joeun.joeunmall.service;

import java.util.List;

import com.joeun.joeunmall.vo.ProductImageVO;
import com.joeun.joeunmall.vo.ProductOptionVO;
import com.joeun.joeunmall.vo.ProductVO;

public interface ProductManageService {

	/**
	 * product_tbl 전체 데이터 가져오기 <br>
	 * ex>한 페이지당 8레코드 <br>
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 한 페이지당 레코드 수
	 * @return 페이지 당 vo
	 */
	public List<ProductVO> getAllProductByPaging(int currentPage, int recordsPerPage);
	
	/**
	 * 게시판 전체 레코드 수
	 * 
	 * @return 게시판 전체 레코드 수
	 */
	public int getAllProductRecordNum();
	
	/**
	 * 상품관리 검색기능 페이징
	 * 
	 * @param currentPage
	 * @param recordsPerPage
	 * @param searchWord
	 * @return
	 */
	public List<ProductVO> getProductSearchByPage(int currentPage, int recordsPerPage, String searchWord);
	
	/**
	 * 검색 결과의 전체 레코드 수
	 * 
	 * @param searchWord 검색어
	 * @return 검색 결과의 전체 레코드 수
	 */
	public int getAllProductRecordNumSearch(String searchWord);
	
	/**
	 * RJA
	 * 상품번호로 상품정보 조회
	 * 
	 * @param productIndex 상품번호
	 * @return 상품정보VO
	 */
	public ProductVO selectProductInfo(String productIndex);
	
	/**
	 * RJA
	 * 상품번호로 상품이미지 조회
	 * 
	 * @param productIndex 상품번호
	 * @return 상품이미지VO
	 */
	public List<ProductImageVO> selectProductImage(String productIndex);
	
	/**
	 * RJA
	 * 상품번호로 상품옵션 조회
	 * 
	 * @param productIndex 상품번호
	 * @return 상품옵션VO
	 */
	public List<ProductOptionVO> selectProductOption(String productIndex);
	
	/**
	 * 카테고리별 상품 정보 조회 - 상품관리 카테고리
	 * 
	 * @param page 현재 페이지 
	 * @param recordsPerPage 페이지당 출력 상품 수
	 * @param productCategoryIndex 카테고리 번호 ex) 01
	 * @return 카테고리별 상품
	 */
	public List<ProductVO> selectProductsByPagingAndCategory(int currentPage, int recordsPerPage, String productCategoryIndex);

	/**
	 * 카테고리별 상품 수 조회 - 상품관리 카테고리
	 * 
	 * @param productCategoryIndex 카테고리 번호 ex) 01
	 * @return 카테고리별 상품 수
	 */
	public int selectProductsCountByCategory(String productCategoryIndex);
}
