package com.joeun.joeunmall.vo;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public class CustomUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String id; // 아이디(custom login id)
	private String username; // 아이디(Spring Security login id)
    private String password;
 
    /* Spring Security related fields */
    // private List<Role> authorities;
    private List<MemberRoleVO> authorities; // 변경
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    
    public CustomUser() { }
	
//    public CustomUser(Users users) {
//		this.username = users.getUsername();
//		this.password = users.getPassword();
//		this.enabled = users.getEnabled()==1 ?  true : false;
//	}
    
    // 추가
    public CustomUser(String id, String password, int enabled) {
		this.username = id;
		this.password = password;
		this.enabled = enabled==1 ?  true : false;
	}
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    // 리턴 변경
    public List<MemberRoleVO> getAuthorities() {
        return authorities;
    }
    // 인자 변경
    public void setAuthorities(List<MemberRoleVO> authorities) {
        this.authorities = authorities;
    }
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	@Override
	public String toString() {
		return "CustomUser [id=" + id + ", username=" + username + ", password=" + password + ", authorities="
				+ authorities + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + "]";
	}

}
