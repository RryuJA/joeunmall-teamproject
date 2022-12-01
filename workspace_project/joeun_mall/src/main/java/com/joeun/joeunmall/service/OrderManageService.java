package com.joeun.joeunmall.service;

import java.util.List;
import java.util.Map;

public interface OrderManageService {

	/**
	 * LSE 관리자-주문관리 Paging <br>
	 * ex>한 페이지당 8레코드 <br>
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 한 페이지당 레코드 수
	 * @return 페이지 당 vo
	 */
	public List<Map<String, Object>> getAllOrderByPaging(int currentPage, int recordsPerPage);
	
	/**
	 * LSE 관리자-주문관리 Paging records 수량 계
	 * 
	 * @return 게시판 전체 레코드 수
	 */
	public int getAllOrderRecordNum();
	
	/**
	 * LSE 관리자-주문관리-검색기능 paging
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 페이지당 레코드수
	 * @param searchWord 검색어
	 * @return 검색결과
	 */
	public List<Map<String, Object>> getOrderSearchByPage(int currentPage, int recordsPerPage, String searchWord);
	
	/**
	 * LSE 관리자-주문관리-검색기능 paging records 수량 계산
	 * 
	 * @param searchWord 검색어
	 * @return 검색결과의 전체 레코드 수
	 */
	public int getAllOrderRecordNumSearch(String searchWord);
	
	/**
	 * LSE 관리자-주문관리-상품명-주문에 포함되는 모든 제품명 출력 ('제품명 외 N개' 출력용)
	 * 
	 * @param orderIndex
	 * @return 주문번호에 포함되는 모든 상품명
	 */
	public List<String> getOrderProductsName(String orderIndex);
	
	/**
	 * LSE 관리자-주문관리-selectBox- 주문상태번호수정(selectBox 제어)
	 * db에 데이터가 반영되었는지 확인하는 역할
	 * @param orderIndex 주문번호
	 * @param orderStateIndex 주문상태번호(ex)STA0, STA1...
	 * @return 수정성공여부
	 */
	public boolean updateOrderState(String orderIndex, String orderStateIndex);
	
	/**
	 * LSE 관리자-주문관리-selectBox-주문번호 존재확인 (selectBox 제어)(log 출력용)
	 * db에 데이터가 반영되었는지 확인하는 역할
	 * @param orderIndex 주문번호
	 * @return 주문번호 존재확인!
	 */
	public boolean getOrderStateCheck(String orderIndex);
}