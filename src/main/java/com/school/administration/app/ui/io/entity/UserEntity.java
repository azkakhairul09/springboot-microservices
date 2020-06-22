package com.school.administration.app.ui.io.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity(name="t_user")
public class UserEntity implements Serializable{
	private static final long serialVersionUID = -4465726475061969211L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="user")
	@TableGenerator(name="user", table="sequence_id",
			pkColumnName="sequence_name", pkColumnValue="userID",
			valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false, length = 120)
	private String username;
	
	@Column(nullable = false)
	private String encryptPassword;
	
	@Column(nullable = true, length = 120)
	private String fullName;
	
	@Column(nullable = false, length = 100)
	private String email;
	
	@Column(nullable = true)
	private String gender;
	
	@Column(nullable = true)
	private String birthPlace;
	
	@Column(nullable = true)
	private String birthDate;
	
	@Column(nullable = true)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String createdDate;
	
	@Column
	private String modifiedBy;
	
	@Column
	private String modifiedDate;
	
	@Column(nullable = false)
	private Boolean isActive;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="role_id")
	private RoleEntity role;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	private Set<InvoiceEntity> invoice;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="address_id")
	private AddressEntity address;
	
	private String token;
	private String loginTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<InvoiceEntity> getInvoice() {
		return invoice;
	}

	public void setInvoice(Set<InvoiceEntity> invoice) {
		this.invoice = invoice;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
}
