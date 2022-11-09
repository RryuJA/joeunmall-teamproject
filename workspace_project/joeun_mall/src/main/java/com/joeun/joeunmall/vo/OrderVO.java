package com.joeun.joeunmall.vo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class OrderVO {

		/** 주문번호 */
		private String orderIndex;
		/** 고객번호 */
		private String userIndex;
		/** 진행상태번호 */
		private	String orderStateIndex;
		/** 결제유형번호 */
		private String paymentIndex;
		/** 주문일자 */
		private Date orderDate;
		/** 총액 */
		private String orderPrice;
		/** 수령인 */
		private String orderName;
		/** 우편번호 */
		private String orderPost;
		/** 주소 */
		private String orderAddress;
		/** 상세주소 */
		private String orderAddressDetail;
		/** 연락처 */
		private String orderMobile;
		/** 배송메시지 */
		private String orderMessage;
		
		//ORDER_TBL(주문 테이블)에 없는 필드  : 다른 테이블(INQUIRY_TBL)과의 조인을 통해서 조회
		/** 문의처리상태 */
		private String inquiryState;
		/** 문의제목 */
		private String inquiryTitle;
		/** 문의내용 */
		private String inquiryContent;
		
		//ORDER_TBL(주문 테이블)에 없는 필드  : 다른 테이블과의 조인을 통해서 조회
		
		/** 주문상품 리스트 */
		private List<CartVO> cartList;
		/** 상품번호 */
		//private List<String> productIndex;
		/** 상품명 */
		//private List<String> productName;
		/** 상품개수 */
		//private List<Integer> productCount;
		/** 상품가격 */
		//private List<Integer> productPrice;
}


