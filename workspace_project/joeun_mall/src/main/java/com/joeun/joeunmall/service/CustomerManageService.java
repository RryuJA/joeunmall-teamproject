package com.joeun.joeunmall.service;

import java.util.List;

import com.joeun.joeunmall.vo.UserVO;

public interface CustomerManageService {
	
	/**
	 * user_tbl 전체 데이터 가져오기  <br>
	 * ex)한 페이지 당 8레코드<br>
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 한 페이지당 레코드 수
	 * @return 페이지 당 vo
	 */
	public List<UserVO> getAllUserByPaging(int currentPage, int recordsPerPage);
	
	/**
	 * 게시판 전체 레코드 수
	 * 
	 * @return 게시판 전체 레코드 수
	 */
	public int getAllUserRecordNum();

}
