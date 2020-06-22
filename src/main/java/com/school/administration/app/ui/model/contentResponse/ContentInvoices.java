package com.school.administration.app.ui.model.contentResponse;

import java.util.List;

import com.school.administration.app.ui.model.response.InvoiceResponse;

public class ContentInvoices {

	private String errorCode;
	private String errorDesc;
	private List<InvoiceResponse> content;
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
	public List<InvoiceResponse> getContent() {
		return content;
	}
	public void setContent(List<InvoiceResponse> content) {
		this.content = content;
	}
	
}
