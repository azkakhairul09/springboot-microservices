package com.school.administration.app.shared.dto;

import java.io.Serializable;

public class QrenNotifPaymentDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2938118245049559568L;
	private String invoice;
	private String status;
	private String amount;
	private String merchantApiKey;
	private String trxId;
	private String qrenTransId;
	private String message;
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMerchantApiKey() {
		return merchantApiKey;
	}
	public void setMerchantApiKey(String merchantApiKey) {
		this.merchantApiKey = merchantApiKey;
	}
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public String getQrenTransId() {
		return qrenTransId;
	}
	public void setQrenTransId(String qrenTransId) {
		this.qrenTransId = qrenTransId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}