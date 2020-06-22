package com.school.administration.app.ui.model.response;

public class RoleResponse {
	private String roleId;
	private String roleName;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
//		if (getRoleId().equals("1")) 
//		{
//			roleName = "Administrator";
//			return roleName;
//		}
//		else if (getRoleId().equals("2")) 
//		{
//			roleName = "Member";
//			return roleName;
//		}
//		
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
