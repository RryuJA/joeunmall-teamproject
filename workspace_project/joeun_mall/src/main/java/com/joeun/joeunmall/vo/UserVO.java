package com.joeun.joeunmall.vo;

import java.util.Date;

public class UserVO {

	/** 고객번호 */
	private String userIndex;
	/** 아이디 */
	private String userId;
	/** 이름 */
	private String userName;
	/** 비밀번호 */
	private String userPw;
	/** 가입일 */
	private Date userDate;
	/** 이메일 */
	private String userMail;
	/** 생년월일 */
	private String userBirth;
	/** 성별 */
	private String userGender;
	/** 일반전화 */
	private String userLandline;
	/** 휴대전화 */
	private String userMobile;
	/** 우편번호 */
	private String userPost;
	/** 주소 */
	private String userAddress;
	/** 상세주소 */
	private String userAddressDetail;
	/** 나이 */
	private int userAge;

	
public UserVO() {

	}

	public UserVO(String userIndex, String userName, Date userDate, int userAge, String userGender,
			String userMobile) {

		this.userIndex = userIndex;
		this.userName = userName;
		this.userDate = userDate;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userMobile = userMobile;

	}

	public String getUserIndex() {
		return userIndex;
	}

	public void setUserIndex(String userIndex) {
		this.userIndex = userIndex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getuserMobile() {
		return userMobile;
	}

	public void setuserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

 

	@Override
	public String toString() {
		return "UserVO [userIndex=" + userIndex + ", userName=" + userName + ", userDate=" + userDate
				+ ", userAge=" + userAge + ", userGender=" + userGender + ", userMobile=" + userMobile + "]";

	}
}
