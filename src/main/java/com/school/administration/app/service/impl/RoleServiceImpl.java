package com.school.administration.app.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.administration.app.exceptions.UserServiceException;
import com.school.administration.app.io.repositories.RoleRepository;
import com.school.administration.app.service.RoleService;
import com.school.administration.app.shared.dto.RoleDto;
import com.school.administration.app.ui.io.entity.RoleEntity;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDto getRole(String roleId) {
		// TODO Auto-generated method stub
		RoleDto returnValue = new RoleDto();
		
		RoleEntity roleEntity = roleRepository.findRoleIdByRoleId(roleId);
		
		if (roleEntity == null) throw new UserServiceException(
				"role id not found");
		
		BeanUtils.copyProperties(roleEntity, returnValue);
		
		return returnValue;
	}

	@Override
	public RoleDto createRole(RoleDto role) {
		// TODO Auto-generated method stub
		if (roleRepository.findRoleIdByRoleId(role.getRoleId()) != null) throw new UserServiceException("role id is duplicate entry");
		if (roleRepository.findRoleNameByRoleName(role.getRoleName()) != null) throw new UserServiceException("role name is duplicate entry");
		
		ModelMapper modelMapper = new ModelMapper();
		RoleEntity roleEntity = modelMapper.map(role, RoleEntity.class);
		
		RoleEntity roleDetail = roleRepository.save(roleEntity);
		
		RoleDto returnValue = modelMapper.map(roleDetail, RoleDto.class);
		
		return returnValue;
	}

}
