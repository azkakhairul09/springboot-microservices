package com.school.administration.app.shared.dto;

import java.io.Serializable;

public class CredsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2938118245049559568L;
	private String token;
	private String userId;
	private String username;
	private String fullname;
	private String role;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}