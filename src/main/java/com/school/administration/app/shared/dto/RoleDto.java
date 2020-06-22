package com.school.administration.app.shared.dto;

import java.util.Set;

public class RoleDto{
	private long id;
	private String roleId;
	private String roleName;
	private Set<UserDto> user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<UserDto> getUser() {
		return user;
	}
	public void setUser(Set<UserDto> user) {
		this.user = user;
	}
}
