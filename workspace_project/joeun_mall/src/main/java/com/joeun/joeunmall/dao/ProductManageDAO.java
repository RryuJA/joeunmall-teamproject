package com.joeun.joeunmall.dao;

import java.util.List;

import com.joeun.joeunmall.vo.ProductImageVO;
import com.joeun.joeunmall.vo.ProductOptionVO;
import com.joeun.joeunmall.vo.ProductVO;

public interface ProductManageDAO {

	/**
	 * product_tbl 전체 데이터 가져오기 <br>
	 * ex>한 페이지당 8레코드 <br>
	 * 
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