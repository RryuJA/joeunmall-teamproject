package com.joeun.joeunmall.dao;

import com.joeun.joeunmall.vo.UserVO;

public interface UserDAO {

	/**
	 * 개별 회원정보 조회
	 * 
	 * @param userId 회원 id
	 * @return 회원정보
	 */
	public UserVO selectUser(String userId);
	
	/**
	 * 최근 고객 번호 조회
	 * 
	 * @return 최근 고객 번호 
	 */
	public String selectMaxUserIndex();
	
	/**
	 * 회원 정보 생성
	 * 
	 * @param userVO 회원 정보 객체
	 */
	public void insertUser(UserVO userVO);
	
	/**
	 * 회원 정보 수정 
	 * 
	 * @param userVO 회원 정보 객체
	 */
	public void updateUser(UserVO userVO);
}
