package com.school.administration.app.ui.model.contentResponse;

import com.school.administration.app.ui.model.response.RoleResponse;

public class ContentRole {

	private String errorCode;
	private String errorDesc;
	private RoleResponse content;
	
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
	public RoleResponse getContent() {
		return content;
	}
	public void setContent(RoleResponse returnValue) {		
		this.content = returnValue;
	}
}
