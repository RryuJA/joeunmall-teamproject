package com.joeun.joeunmall.service;

import java.util.List;

import com.joeun.joeunmall.vo.OrderVO;
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
	
	/**
	 * LSE 사용자-마이페이지-주문내역 Paging(user_index로 주문 조회)
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 페이지당 레코드수
	 * @param userIndex 로그인한 유저번호
	 * @return
	 */
	public List<OrderVO> selectMypageUserIndex(int currentPage, int recordsPerPage, String userIndex);
	
	/**
	 * LSE 사용자-마이페이지-주문내역 Paging records records 수량 계산(user_index로 주문 조회)
	 * 
	 * @return 게시판 전체 레코드 수
	 */
	public int selectMypageUserIndexNum(String orderIndex);
	
	/**
	 * LSE 사용자-마이페이지-주문내역-상품명 (모든 상품명 조회 ('파란색티셔츠 외 N개' 형식으로 출력용)
	 * 
	 * @param orderIndex
	 * @return 주문번호에 포함되는 모든 상품명
	 */
	public List<String> getOrderProductsName(String orderIndex);
}
