package com.school.administration.app.shared.dto;

import java.io.Serializable;
import java.util.Set;

public class ProductsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4028739186027019969L;
	private long id;
	private String productId;
	private String productName;
	private String price;
	private String quantity;
	private String description;
	private String expiredDate;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private Boolean isExpired;
	private Set<InvoiceDto> invoice;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Boolean getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}
	public Set<InvoiceDto> getInvoice() {
		return invoice;
	}
	public void setInvoice(Set<InvoiceDto> invoice) {
		this.invoice = invoice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
