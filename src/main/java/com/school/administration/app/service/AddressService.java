package com.school.administration.app.service;

import com.school.administration.app.shared.dto.AddressDto;

public interface AddressService {
	AddressDto getDetailAddress(String addressId);
}
