package com.school.administration.app.shared.dto;

import java.io.Serializable;

public class ForbiddenDto implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5080461331636521447L;

	private String errorCode;
	private String errorDesc;
	
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
	
	
}
