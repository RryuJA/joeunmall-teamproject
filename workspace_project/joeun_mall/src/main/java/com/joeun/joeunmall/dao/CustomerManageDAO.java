package com.joeun.joeunmall.dao;

import java.util.List;

import com.joeun.joeunmall.vo.UserVO;

public interface CustomerManageDAO {

	/**
	 * 고객관리페이지 페이징  <br>
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

	/**
	 * 고객관리 검색기능 페이징
	 * 
	 * @param currentPage
	 * @param recordsPerPage
	 * @param searchWord
	 * @return
	 */
	public List<UserVO> getSearchByPage(int currentPage, int recordsPerPage, String searchWord);
	
	/**
	 * 검색 결과의 전체 레코드 수
	 * 
	 * @param searchWord 검색어
	 * @return 검색 결과의 전체 레코드 수
	 */
	public int getAllUserRecordNumSearch(String searchWord);
}
