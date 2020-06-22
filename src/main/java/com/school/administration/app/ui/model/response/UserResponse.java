package com.school.administration.app.ui.model.response;

public class UserResponse {
	
	private String userId;
	private String fullName;
	private String username;
	private String email;
	private RoleResponse role;
	private String gender;
	private String birthPlace;
	private String birthDate;
	private String phoneNumber;
	private Boolean isActive;
	private String createdDate;
	private AddressResponse address;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
	public RoleResponse getRole() {
		return role;
	}
	public void setRole(RoleResponse role) {
		this.role = role;
	}
	public AddressResponse getAddress() {
		return address;
	}
	public void setAddress(AddressResponse address) {
		this.address = address;
	}
}
