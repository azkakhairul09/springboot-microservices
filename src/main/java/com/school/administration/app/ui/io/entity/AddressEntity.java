package com.school.administration.app.ui.io.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity(name="t_user_address")
public class AddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1227177082633578253L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="t_user_address")
	@TableGenerator(name="t_user_address", table="sequence_id",
			pkColumnName="sequence_name", pkColumnValue="addressID",
			valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	
	private String addressId;
	
	private String fullAddress;
	
	private String province;
	
	private String city;
	
	private String district;
	
	private String subDistrict;
	
	private String postalCode;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	private Set<UserEntity> user;

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

	public Set<UserEntity> getUser() {
		return user;
	}

	public void setUser(Set<UserEntity> user) {
		this.user = user;
	}
}
