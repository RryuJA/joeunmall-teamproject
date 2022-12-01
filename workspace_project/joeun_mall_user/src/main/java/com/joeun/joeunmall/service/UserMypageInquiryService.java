package com.joeun.joeunmall.service;

import java.util.List;

import com.joeun.joeunmall.vo.InquiryVO;

public interface UserMypageInquiryService {
	
	/**
	 * 사용자-마이페이지-내문의글 Paging
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 한 페이지당 레코드 수
	 * @param userIndex 고객번호
	 * @return 페이지 당 vo
	 */
	public List<InquiryVO> selectMyPageInquiryIndex(int currentPage, int recordsPerPage, String userIndex);
	
	/**
	 * 사용자-마이페이지-내문의글 Paging records 수량 계산
	 * 
	 * @param userIndex 고객번호
	 * @return 게시판 전체 레코드 수
	 */
	public int selectMyPageInquiryIndexNum(String userIndex);
}
