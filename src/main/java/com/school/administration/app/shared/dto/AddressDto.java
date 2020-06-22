package com.school.administration.app.shared.dto;

import java.io.Serializable;
import java.util.Set;

public class AddressDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5803044762719999255L;
	private long id;
	private String addressId;
	private String fullAddress;
	private String province;
	private String city;
	private String district;
	private String subDistrict;
	private String postalCode;
	private Set<UserDto> user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSubDistrict() {
		return subDistrict;
	}
	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Set<UserDto> getUser() {
		return user;
	}
	public void setUser(Set<UserDto> userDto) {
		this.user = userDto;
	}
}
