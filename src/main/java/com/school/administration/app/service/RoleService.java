package com.school.administration.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.school.administration.app.shared.dto.RoleDto;

public interface RoleService extends UserDetailsService {
	RoleDto getRole(String id);
}
