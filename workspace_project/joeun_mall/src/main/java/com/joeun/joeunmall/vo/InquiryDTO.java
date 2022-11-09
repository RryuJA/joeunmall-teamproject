package com.joeun.joeunmall.vo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InquiryDTO {

	/** 문의번호 */
	private String inquiryIndex;
	/** 고객번호 */
	private String userIndex;
	/** 문의일자 */
	private String inquiryDate;
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


}
	