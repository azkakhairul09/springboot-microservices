package com.school.administration.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.school.administration.app.shared.dto.ProductsDto;
import com.school.administration.app.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto getUser(String username);
	UserDto createUser(UserDto user);
	ProductsDto createProduct(ProductsDto product);
	List<ProductsDto> getProducts(int page, int limit);
	UserDto getUserByUserId(String userId);
	ProductsDto getProductByProductId(String productId);
	UserDto disactiveUser(String userId, UserDto userDto);
	UserDto updateDetailUser(String userId, UserDto userDto);
	List<UserDto> getUsers();
}
