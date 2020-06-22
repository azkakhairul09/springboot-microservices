package com.school.administration.app.ui.io.entity;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "t_product")
@DynamicUpdate
public class ProductsEntity implements Comparable<ProductsEntity> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="products")
	@TableGenerator(name="products", table="sequence_id",
	pkColumnName="sequence_name", pkColumnValue="productID",
	valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	
	@Column(nullable = false)
	private String productId;
	
	@Column(nullable = false, length = 120, unique = true)
	private String productName;
	
	@Column(nullable = false)
	private String price;
	
	@Column(nullable = false)
	private String quantity;
	
	@Column(nullable = false)
	private String expiredDate;
	
	@Column
	private String description;
	
	@Column(nullable = false)
	private String createdBy;
	
	@Column(nullable = false)
	private String createdDate;
	
	@Column
	private String modifiedBy;
	
	@Column
	private String modifiedDate;
	
	@Column(nullable = false)
	private Boolean isExpired;
	
	@OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
	private Set<InvoiceEntity> invoice;

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

	public Set<InvoiceEntity> getInvoice() {
		return invoice;
	}

	public void setInvoice(Set<InvoiceEntity> invoice) {
		this.invoice = invoice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(ProductsEntity o) {
		// TODO Auto-generated method stub
		return getCreatedDate().compareTo(o.getCreatedDate());
	}
}
