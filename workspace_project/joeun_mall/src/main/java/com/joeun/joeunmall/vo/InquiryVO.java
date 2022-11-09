package com.joeun.joeunmall.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class InquiryVO {

	/** 문의번호 */
	private String inquiryIndex;
	/** 고객번호 */
	private String userIndex;
	/** 문의일자 */
	private Date inquiryDate;
	/** 문의항목 */
	private String inquiryCategory;
	/** 문의처리상태 */
	private String inquiryState;
	/** 문의제목 */
	private String inquiryTitle;
	/** 문의내용 */
	private String inquiryContent;
	/** 문의답변 */
	private String inquiryAnswer;
	
	//INQUIRY_TBL(문의 테이블)에 없는 필드  : 다른 테이블(USER_TBL,PRODUCT_TBL)과의 조인을 통해서 조회
	/** 고객명 */
	private String userName;
	/** 상품명 */
	private String productName; 
	/** 상품번호 */
	private String productIndex; 
	

}