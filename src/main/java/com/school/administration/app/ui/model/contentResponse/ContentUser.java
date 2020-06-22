package com.school.administration.app.ui.model.contentResponse;

import com.school.administration.app.ui.model.response.UserResponse;

public class ContentUser {

	private String errorCode;
	private String errorDesc;
	private UserResponse content;
	
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
	public UserResponse getContent() {
		return content;
	}
	public void setContent(UserResponse returnValue) {		
		this.content = returnValue;
	}
}
