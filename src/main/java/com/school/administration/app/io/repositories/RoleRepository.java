package com.school.administration.app.io.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.RoleEntity;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {
	RoleEntity findRoleByRoleId(String roleId);
	RoleEntity findRoleIdByRoleId(String roleId);
	RoleEntity findRoleNameByRoleName(String roleName);
	String findRoleNameByRoleId(String roleId);
}

