package com.school.administration.app.ui.model.request;

public class TransactionRequestModel{

	private long id;
	private String transactionId;
	private String invoiceId;
	private String productId;
	private String userId;
	private String amount;
	private String info;
	private String solvedDate;
	private String status;
	private String userChecking;
	private Boolean trxVerified;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getSolvedDate() {
		return solvedDate;
	}
	public void setSolvedDate(String solvedDate) {
		this.solvedDate = solvedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserChecking() {
		return userChecking;
	}
	public void setUserChecking(String userChecking) {
		this.userChecking = userChecking;
	}
	public Boolean getTrxVerified() {
		return trxVerified;
	}
	public void setTrxVerified(Boolean trxVerified) {
		this.trxVerified = trxVerified;
	}
}
