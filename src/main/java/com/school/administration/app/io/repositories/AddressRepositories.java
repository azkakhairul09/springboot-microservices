package com.school.administration.app.io.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.AddressEntity;

@Repository
public interface AddressRepositories extends PagingAndSortingRepository<AddressEntity, Long> {
	AddressEntity findByAddressId(String addressId);
}
