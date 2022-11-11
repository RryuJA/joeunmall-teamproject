package com.joeun.joeunmall.vo;

 

import org.springframework.security.core.GrantedAuthority;
 

/**
 * 회원 롤 정보 값 객체(VO)
 * @author java
 *
 */

public class MemberRoleVO implements GrantedAuthority { 
	// 추가 : implements GrantedAuthority

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 아이디 (sequence) */
	private int memberRoleId;

	/** 회원 아이디 */
	private String id;

	/** 회원 아이디(Spring Security용) */
	private String username;

	/** 회원 롤(Role) */
	private String role;

	public int getMemberRoleId() {
		return memberRoleId;
	}

	public void setMemberRoleId(int memberRoleId) {
		this.memberRoleId = memberRoleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "MemberRoleVO [memberRoleId=" + memberRoleId + ", id=" + id + ", username=" + username + ", role=" + role
				+ "]";
	}
	@Override
	public String getAuthority() {
		return this.role;
	}

	

}