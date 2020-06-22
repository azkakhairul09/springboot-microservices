package com.school.administration.app.ui.model.contentResponse;

import com.school.administration.app.ui.model.response.ProductResponse;

public class ContentProduct {

	private String errorCode;
	private String errorDesc;
	private ProductResponse content;
	
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
	public ProductResponse getContent() {
		return content;
	}
	public void setContent(ProductResponse content) {		
		this.content = content;
	}
}
