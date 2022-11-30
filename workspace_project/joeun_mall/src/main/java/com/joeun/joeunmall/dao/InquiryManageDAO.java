package com.joeun.joeunmall.dao;

import java.util.List;

import com.joeun.joeunmall.vo.InquiryVO;

public interface InquiryManageDAO {

	/**
	 * 1:1문의관리페이지 페이징 <br>
	 * ex)한 페이지 당 8레코드<br>
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 한 페이지당 레코드 수
	 * @return 페이지 당 vo
	 */
	public List<InquiryVO> getAllInquiryByPaging(int currentPage, int recordsPerPage);
	
	/**
	 * 게시판 전체 레코드 수
	 * 
	 * @return 게시판 전체 레코드 수
	 */
	public int getAllInquiryRecordNum();
	
	/**
	 * 1:1문의 검색기능
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 페이지당 레코드수
	 * @param searchWord 검색어
	 * @return 검색결과
	 */
	public List<InquiryVO> getInquirySearchByPage(int currentPage, int recordsPerPage, String searchWord);
	
	/**
	 * 검색 결과의 전체 레코드 수
	 * 
	 * @param searchWord 검색어
	 * @return 검색결과의 전체 레코드
	 */
	public int getAllInquiryRecordNumSearch(String searchWord);
	
	/**
	 * LSE
	 * 문의번호로 문의상세정보조회
	 * 
	 * @param inquiryIndex 문의번호
	 * @return 문의상세정보VO
	 */
	public InquiryVO selectInquiryData(String inquiryIndex);
	

}
