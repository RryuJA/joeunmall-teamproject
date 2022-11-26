package com.joeun.joeunmall.service;

import com.joeun.joeunmall.vo.UserVO;

public interface UserService {

	/**
	 * 
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
	public int selectMaxUserIndex();
	
	/**
	 * 회원 정보 생성
	 * 
	 * @param userVO 회원 정보 객체
	 * @return 저장여부
	 */
	public boolean insertUser(UserVO userVO);
	
	/**
	 * 회원 정보 수정 
	 * 
	 * @param userVO 회원 정보 객체
	 * @return 수정여부
	 */
	public boolean updateUser(UserVO userVO);
	
	/**
	 * 회원 정보 삭제
	 * 
	 * @param userId 고객 아이디
	 * @return 삭제여부
	 */
	public boolean deleteUser(String userId);
}
