package com.school.administration.app.ui.io.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity(name="t_qren_notif")
public class QrenNotifEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1227177082633578253L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="t_qren_notif")
	@TableGenerator(name="t_qren_notif", table="sequence_id",
			pkColumnName="sequence_name", pkColumnValue="qrenNotifID",
			valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	private String invoiceId;
	private String status;
	private String amount;
	private String merchantApiKey;
	private String trxId;
	private String qrenTransId;
	private String message;
	private String timeStamp;
	private String userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
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

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
