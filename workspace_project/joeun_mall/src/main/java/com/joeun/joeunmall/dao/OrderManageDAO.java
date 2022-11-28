package com.joeun.joeunmall.dao;

import java.util.List;
import java.util.Map;

public interface OrderManageDAO {

	/**
	 * 주문관리 paging <br>
	 * ex>한 페이지당 8레코드 <br>
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 한 페이지당 레코드 수
	 * @return 페이지 당 vo
	 */
	public List<Map<String, Object>> getAllOrderByPaging(int currentPage, int recordsPerPage);
	
	/**
	 * 주문관리 전체 레코드 수
	 * 
	 * @return 게시판 전체 레코드 수
	 */
	public int getAllOrderRecordNum();
	
	/**
	 * 주문상태번호 수정
	 * 
	 * @param orderIndex 주문번호
	 * @param orderStateIndex 주문상태번호(ex)STA0, STA1...
	 */
	public void updateOrderState(String orderIndex, String orderStateIndex);
	
	/**
	 * 주문번호 존재확인
	 * 
	 * @param orderIndex 주문번호
	 * @return 주문번호 존재확인!
	 */
	public int getOrderStateCheck(String orderIndex);
	
	/**
	 * 주문번호에 포함되는 모든 제품명을 뽑는 쿼리
	 * 
	 * @param orderIndex
	 * @return 주문번호에 포함되는 모든 상품명
	 */
	public List<String> getOrderProductsName(String orderIndex);
	
	/**
	 * 주문관리의 검색기능
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 페이지당 레코드수
	 * @param searchWord 검색어
	 * @return 검색결과
	 */
	public List<Map<String, Object>> getOrderSearchByPage(int currentPage, int recordsPerPage, String searchWord);
	
	/**
	 * 검색 결과의 전체 레코드 수
	 * 
	 * @param searchWord 검색어
	 * @return 검색결과의 전체 레코드 수
	 */
	public int getAllOrderRecordNumSearch(String searchWord);
}
