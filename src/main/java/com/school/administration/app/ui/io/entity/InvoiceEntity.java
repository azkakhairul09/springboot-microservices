package com.school.administration.app.ui.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity(name = "t_invoice")
public class InvoiceEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7757876469079712673L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="invoice")
	@TableGenerator(name="invoice", table="sequence_id",
	pkColumnName="sequence_name", pkColumnValue="invoiceID",
	valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	
	@Column(nullable = false)
	private String invoiceId;
	
	@Column(nullable = false, length = 120)
	private String invoiceName;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="product_id")
	private ProductsEntity productId;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="user_id")
	private UserEntity userId;
	
	@Column(nullable = false)
	private String nominal;
	
	@Column(nullable = false)
	private String info;
	
	@Column(nullable = false)
	private String invoiceDate;
	
	@Column(nullable = false)
	private Boolean isPayment;
	
	@Column(nullable = false)
	private String createdBy;
	
	@Column
	private String modifiedBy;
	
	@Column(nullable = false)
	private String createdDate;
	
	@Column
	private String modifiedDate;
	
	@Column
	private String qrContent;
	
	@Column
	private Boolean isExpired;
	
	@Column
	private String transactionId;
	
	@Column
	private String solvedDate;
	
	@Column
	private Boolean isVerified;
	
	

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

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public ProductsEntity getProductId() {
		return productId;
	}

	public void setProductId(ProductsEntity productId) {
		this.productId = productId;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public String getNominal() {
		return nominal;
	}

	public void setNominal(String nominal) {
		this.nominal = nominal;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Boolean getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Boolean isPayment) {
		this.isPayment = isPayment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getSolvedDate() {
		return solvedDate;
	}

	public void setSolvedDate(String solvedDate) {
		this.solvedDate = solvedDate;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}	
}
