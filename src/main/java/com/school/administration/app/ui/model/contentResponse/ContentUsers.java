package com.school.administration.app.ui.model.contentResponse;

import java.util.List;

import com.school.administration.app.ui.model.response.UserResponse;

public class ContentUsers {

	private String errorCode;
	private String errorDesc;
	private List<UserResponse> content;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public List<UserResponse> getContent() {
		return content;
	}
	public void setContent(List<UserResponse> content) {		
		this.content = content;
	}
}
