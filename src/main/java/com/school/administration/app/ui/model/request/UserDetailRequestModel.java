package com.school.administration.app.ui.model.request;

public class UserDetailRequestModel {
	
	private String fullName;
	private String gender;
	private String birthPlace;
	private String birthDate;
	private String phoneNumber;
	private AddressRequestModel address;
	
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
	public AddressRequestModel getAddress() {
		return address;
	}
	public void setAddress(AddressRequestModel address) {
		this.address = address;
	}
}
