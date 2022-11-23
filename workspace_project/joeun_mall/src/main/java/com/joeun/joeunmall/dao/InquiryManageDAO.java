package com.joeun.joeunmall.dao;

import java.util.List;

import com.joeun.joeunmall.vo.InquiryVO;

public interface InquiryManageDAO {

	/**
	 * user_tbl 전체 데이터 가져오기  <br>
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
}
