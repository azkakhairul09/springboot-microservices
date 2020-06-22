package com.school.administration.app.ui.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity(name = "user_transaction")
public class TransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6195028329283200785L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="transaction")
	@TableGenerator(name="transaction", table="sequence_id",
	pkColumnName="sequence_name", pkColumnValue="transactionID",
	valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	
	@Column(nullable = false)
	private String transactionId;
	
	@Column(nullable = false)
	private String invoiceId;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String amount;
	
	@Column(nullable = false)
	private String info;
	
	@Column(nullable = false)
	private String solvedDate;
	
	@Column(nullable = false)
	private String status;
	
	@Column
	private String adminChecking;
	
	@Column
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

	public String getAdminChecking() {
		return adminChecking;
	}

	public void setAdminChecking(String adminChecking) {
		this.adminChecking = adminChecking;
	}

	public Boolean getTrxVerified() {
		return trxVerified;
	}

	public void setTrxVerified(Boolean trxVerified) {
		this.trxVerified = trxVerified;
	}
}
