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
	/** 고객명 */
	private String userName;
	
	public InquiryVO() {
		
	}
	
	public InquiryVO(String inquiryIndex, String userIndex, Date inquiryDate, String inquiryCategory,
			String inquiryState, String inquiryTitle, String inquiryContent, String inquiryAnswer, String userName) {

		this.inquiryIndex = inquiryIndex;
		this.userIndex = userIndex;
		this.inquiryDate = inquiryDate;
		this.inquiryCategory = inquiryCategory;
		this.inquiryState = inquiryState;
		this.inquiryTitle = inquiryTitle;
		this.inquiryContent = inquiryContent;
		this.inquiryAnswer = inquiryAnswer;
		this.userName = userName;
	}

}