package com.myclass.dto;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginDto extends User {

	private static final long serialVersionUID = 1L;
	
	private String password;
	private String username;
	private Set<GrantedAuthority> authorities;
	
	private String fullname;
	private String avatar;
	private int userId;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public LoginDto(String username, String password, Collection<? extends GrantedAuthority> authorities, String avatar, int userId) {
		super(username, password, authorities);
		this.userId = userId;
		this.avatar = avatar;
		this.username = username;
	    this.password = password;
	    this.authorities = (Set<GrantedAuthority>) authorities;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	
	
}
