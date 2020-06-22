package com.school.administration.app.ui.model.response;

public class InvoiceResponse {
	private String invoiceId;
	private Boolean isExpired;
	private Boolean isPayment;
	private String invoiceName;
	private String nominal;
	private String productName;
	private String invoiceDate;
	private String qrContent;
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getNominal() {
		return nominal;
	}
	public void setNominal(String nominal) {
		this.nominal = nominal;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getQrContent() {
		return qrContent;
	}
	public void setQrContent(String qrContent) {
		this.qrContent = qrContent;
	}
	public Boolean getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}
	public Boolean getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(Boolean isPayment) {
		this.isPayment = isPayment;
	}
}
