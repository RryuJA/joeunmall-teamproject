package com.joeun.joeunmall.vo;

import java.util.Date;

/**
 * 고객정보 VO(값 객체)
 * 
 * @author team3
 */

public class CustomerManageVO {

	/** 고객번호 */
	private String userIndex;
	
	/** 고객명 */
	private String userName;
	
	/** 가입일 */
	private Date userDate;
	
	/** 나이 */
	private int userAge;
	
	/** 성별 */
	private String userGender;
	
	/** 연락처 */
	private String userMoblie;
	
	public CustomerManageVO() {
		
	}

	public CustomerManageVO(String userIndex, String userName, Date userDate, int userAge, String userGender,
			String userMoblie) {
		
		this.userIndex = userIndex;
		this.userName = userName;
		this.userDate = userDate;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userMoblie = userMoblie;
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

	public String getUserMoblie() {
		return userMoblie;
	}

	public void setUserMoblie(String userMoblie) {
		this.userMoblie = userMoblie;
	}

	@Override
	public String toString() {
		return "CustomerManageVO [userIndex=" + userIndex + ", userName=" + userName + ", userDate=" + userDate
				+ ", userAge=" + userAge + ", userGender=" + userGender + ", userMoblie=" + userMoblie + "]";
	}
	
	
}
