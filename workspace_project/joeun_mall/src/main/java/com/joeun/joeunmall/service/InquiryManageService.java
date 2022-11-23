package com.joeun.joeunmall.service;

import java.util.List;

import com.joeun.joeunmall.vo.InquiryVO;

public interface InquiryManageService {
	/**
	 * @author LSE
	 * inquiry_tbl 전체 데이터 가져오기.
	 * ex)한 페이지당 7레코드
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordPerPage 한 페이지 당 레코드(줄) 수
	 * @return 페이지 당 vo
	 * 
	 * */
	
	public List<InquiryVO> getAllInquiryByPaging(int currentPage, int recordPerPage);
	
	/**
	 * 게시판 전체 레코드 수 계산
	 * 
	 * 
	 * @return 게시판 전체 레코드 수(int)
	 * 
	 * */
	
	public int getAllInquiryRecordNum();
}
