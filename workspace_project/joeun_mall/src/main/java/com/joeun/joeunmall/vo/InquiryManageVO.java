package com.joeun.joeunmall.vo;

import java.util.Date;

/**
 * InquiryManage 1:1문의정보 VO(값 객체)
 * 
 * @author Team3
 * */

public class InquiryManageVO {

	/** 문의번호 */
	private String inquiryIndex;
	/** 문의일자 */
	private Date inquiryDate;
	/** 고객명 */
	private String userName;
	/** 문의항목 */
	private String inquiryCategory;
	/** 문의제목 */
	private String inquiryTitle;
	
	public InquiryManageVO() {
		
	}

	public InquiryManageVO(String inquiryIndex, Date inquiryDate, String userName, String inquiryCategory,
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
		return "InquiryManageVO [inquiryIndex=" + inquiryIndex + ", inquiryDate=" + inquiryDate + ", userName="
				+ userName + ", inquiryCategory=" + inquiryCategory + ", inquiryTitle=" + inquiryTitle + "]";
	}
	
	
}
