package com.school.administration.app.ui.model.contentResponse;

import com.school.administration.app.ui.model.response.InvoiceResponse;

public class ContentInvoice {
	
	private String errorCode;
	private String errorDesc;
	private InvoiceResponse content;
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
	public InvoiceResponse getContent() {
		return content;
	}
	public void setContent(InvoiceResponse content) {
		this.content = content;
	}
}
