package com.school.administration.app.ui.model.contentResponse;

import java.util.List;

import com.school.administration.app.ui.model.response.ProductResponse;

public class ContentProducts {

	private String errorCode;
	private String errorDesc;
	private List<ProductResponse> content;
	
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
	public List<ProductResponse> getContent() {
		return content;
	}
	public void setContent(List<ProductResponse> content) {		
		this.content = content;
	}
}
