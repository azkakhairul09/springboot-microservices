package com.school.administration.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.administration.app.ui.io.entity.AddressEntity;
import com.school.administration.app.ui.io.entity.ProductsEntity;
import com.school.administration.app.ui.io.entity.RoleEntity;
import com.school.administration.app.ui.io.entity.UserEntity;
import com.school.administration.app.ScheduledTasks;
import com.school.administration.app.exceptions.UserServiceException;
import com.school.administration.app.io.repositories.AddressRepositories;
import com.school.administration.app.io.repositories.ProductsRepository;
import com.school.administration.app.io.repositories.RoleRepository;
import com.school.administration.app.io.repositories.UserRepository;
import com.school.administration.app.shared.Utils;
import com.school.administration.app.service.UserService;
import com.school.administration.app.shared.dto.AddressDto;
import com.school.administration.app.shared.dto.ProductsDto;
import com.school.administration.app.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ProductsRepository productRepository;
	
	@Autowired
	AddressRepositories addressRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	ScheduledTasks scheduledTasks;
	
	@Autowired
	Utils utils;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if (userEntity == null) throw new UsernameNotFoundException("user not found");
		
		if (userEntity.getIsActive().equals(false)) throw new UserServiceException("user is not active");
		
		return new User(userEntity.getEmail(), userEntity.getEncryptPassword(), 
				userEntity.getIsActive(),
				true, true,
				true, new ArrayList<>());
	}

	@Override
	public UserDto getUser(String username) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUsername(username);
		
		if (userEntity == null) throw new UserServiceException(username+ " not found");
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
				
		return returnValue;
	}

	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		//validation
		if (userRepository.findByUsername(user.getUsername()) != null )
		throw new UserServiceException("username is duplicate entry");
		
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new UserServiceException("email is duplicate entry");
		
		final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		
		String timeStr = formatter.format(currentTime.getTime());
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		
		RoleEntity roleEntity = roleRepository.findRoleByRoleId("2");
		
		String publicUserId = utils.generateUserId(9);
		userEntity.setEncryptPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		userEntity.setIsActive(true);
		userEntity.setCreatedDate(timeStr);
		userEntity.setRole(roleEntity);
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);
		
		return returnValue;
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		// TODO Auto-generated method stub
		List<UserDto> returnValue = new ArrayList<UserDto>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		if (page>0) page = page-1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		
		List<UserEntity> users = usersPage.getContent();
		
		for (UserEntity userEntity : users) {
			returnValue.add(modelMapper.map(userEntity, UserDto.class) );
		}
		
		return returnValue;
	}

	@Override
	public UserDto updateDetailUser(String userId, UserDto userDto) {
		// TODO Auto-generated method stub
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findUserByUserId(userId);
		
		if (userEntity == null) throw new UserServiceException("user not found");
		if (userEntity.getIsActive() == false) throw new UserServiceException("user is not active");
		
		final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String timeStr = formatter.format(currentTime.getTime());
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
		
		AddressDto address = userDto.getAddress();
		if (address == null) throw new UserServiceException("address not found");
		
		ModelMapper modelMapper = new ModelMapper();
		
		if (userEntity.getAddress() != null) 
		{
			String addressId = userEntity.getAddress().getAddressId();
			AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
			
//			addressEntity.setFullAddress(address.getFullAddress());
//			addressEntity.setCity(address.getCity());
//			addressEntity.setDistrict(address.getDistrict());
//			addressEntity.setProvince(address.getProvince());
//			addressEntity.setPostalCode(address.getPostalCode());
//			addressEntity.setSubDistrict(address.getSubDistrict());
			
			addressRepository.save(addressEntity);
			
			userEntity.setFullName(userDto.getFullName());
			userEntity.setGender(userDto.getGender());
			userEntity.setBirthPlace(userDto.getBirthPlace());
			userEntity.setBirthDate(userDto.getBirthDate());
			userEntity.setPhoneNumber(userDto.getPhoneNumber());
			userEntity.setModifiedDate(timeStr);
			userEntity.setModifiedBy(authentication.getName());
			
			UserEntity updateDetailUser = userRepository.save(userEntity);
			
			returnValue = modelMapper.map(updateDetailUser, UserDto.class);
		}		
		else if (userEntity.getAddress() == null) 
		{
			address.setAddressId(utils.generateAddressId(5));
			
			AddressEntity addressEntity = modelMapper.map(address, AddressEntity.class);
			userEntity.setAddress(addressEntity);
			addressRepository.save(addressEntity);
			
			userEntity.setFullName(userDto.getFullName());
			userEntity.setGender(userDto.getGender());
			userEntity.setBirthPlace(userDto.getBirthPlace());
			userEntity.setBirthDate(userDto.getBirthDate());
			userEntity.setPhoneNumber(userDto.getPhoneNumber());
			userEntity.setModifiedDate(timeStr);
			userEntity.setModifiedBy(authentication.getName());
			
			UserEntity updateDetailUser = userRepository.save(userEntity);
			
			returnValue = modelMapper.map(updateDetailUser, UserDto.class);
		}
		
		return returnValue;
	}
	
	@Override
	public UserDto disactiveUser(String userId, UserDto userDto) {
		// TODO Auto-generated method stub
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findUserByUserId(userId);
		
		if (userEntity == null) throw new UserServiceException("user not found");
		if (userEntity.getIsActive() == false) throw new UserServiceException("user is not active");
		
		final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String timeStr = formatter.format(currentTime.getTime());
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        
		userEntity.setIsActive(false);
		userEntity.setModifiedDate(timeStr);
		userEntity.setModifiedBy(authentication.getName());
		
		UserEntity disactiveUser = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(disactiveUser, returnValue);
		
		return returnValue;
	} 

	@Override
	public ProductsDto createProduct(ProductsDto product) {
		// TODO Auto-generated method stub
		if (productRepository.findByProductName(product.getProductName()) != null) throw new UserServiceException("product is duplicate entry");
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
		
		final String DATE_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String timeStr = formatter.format(currentTime.getTime());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date d1 = null;
		Date d2 = null;

		ModelMapper modelMapper = new ModelMapper();
		ProductsEntity productEntity = modelMapper.map(product, ProductsEntity.class);
		
		try {
			d1 = format.parse(productEntity.getExpiredDate());
			d2 = format.parse(timeStr);

			//in milliseconds
			long diff = d1.getTime() - d2.getTime();
			
			char first = String.valueOf(diff).charAt(0);
			
			if (first == '-') 
			{
				productEntity.setIsExpired(true);
			} 
			else if (diff == 0)
			{
				productEntity.setIsExpired(true);
			}
			else 
			{
				productEntity.setIsExpired(false);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		String productID = utils.generateProductId(5);
		
		productEntity.setProductId(productID);
		productEntity.setCreatedBy(authentication.getName());
		productEntity.setCreatedDate(timeStr);
		
		ProductsEntity productDetail = productRepository.save(productEntity);
		
		ProductsDto returnValue = modelMapper.map(productDetail, ProductsDto.class);
		return returnValue;
	}

	@Override
	public List<ProductsDto> getProducts(int page, int limit) {
		// TODO Auto-generated method stub
		List<ProductsDto> returnValue = new ArrayList<ProductsDto>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		if (page>0) page = page-1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductsEntity> productsPage = productsRepository.findAllByOrderByCreatedDateDesc(pageableRequest);
		
		List<ProductsEntity> products = productsPage.getContent();
		
		for (ProductsEntity productEntity : products) {
			returnValue.add(modelMapper.map(productEntity, ProductsDto.class) );
		}
		
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		UserDto returnValue = new UserDto();
		
		ModelMapper modelMapper = new ModelMapper();
		
		UserEntity userEntity = userRepository.findUserByUserId(userId);
		
		if (userEntity == null) throw new UserServiceException(
				"user not found");
		
		returnValue = modelMapper.map(userEntity, UserDto.class);
		
		return returnValue;
	}

	@Override
	public ProductsDto getProductByProductId(String productId) {
		// TODO Auto-generated method stub
		ProductsDto returnValue = new ProductsDto();
		
		ModelMapper modelMapper = new ModelMapper();
		
		ProductsEntity productEntity = productsRepository.findProductByProductId(productId);
		
		if (productEntity == null) throw new UserServiceException(
				"product not found");
		
		returnValue = modelMapper.map(productEntity, ProductsDto.class);
		return returnValue;
	}

	
}
