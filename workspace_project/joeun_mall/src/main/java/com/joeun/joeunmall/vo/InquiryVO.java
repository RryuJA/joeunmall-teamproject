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

	public InquiryVO(String inquiryIndex, Date inquiryDate, String userName, String inquiryCategory,
			String inquiryTitle) {
		this.inquiryIndex = inquiryIndex;
		this.inquiryDate = inquiryDate;
		this.userName = userName;
		this.inquiryCategory = inquiryCategory;
		this.inquiryTitle = inquiryTitle;
	}

	public String getInquiryIndex() {
		return inquiryIndex;
	}

	public void setInquiryIndex(String inquiryIndex) {
		this.inquiryIndex = inquiryIndex;
	}

	public Date getInquiryDate() {
		return inquiryDate;
	}

	public void setInquiryDate(Date inquiryDate) {
		this.inquiryDate = inquiryDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInquiryCategory() {
		return inquiryCategory;
	}

	public void setInquiryCategory(String inquiryCategory) {
		this.inquiryCategory = inquiryCategory;
	}

	public String getInquiryTitle() {
		return inquiryTitle;
	}

	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}

	@Override
	public String toString() {
		return "InquiryVO [inquiryIndex=" + inquiryIndex + ", inquiryDate=" + inquiryDate + ", userName="
				+ userName + ", inquiryCategory=" + inquiryCategory + ", inquiryTitle=" + inquiryTitle + "]";
	}
}