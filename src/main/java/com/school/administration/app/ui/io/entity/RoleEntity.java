package com.school.administration.app.ui.io.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity(name="t_role")
public class RoleEntity implements Serializable{
	private static final long serialVersionUID = -4465726475061969211L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="role")
	@TableGenerator(name="role", table="sequence_id",
			pkColumnName="sequence_name", pkColumnValue="roleID",
			valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	
	@Column(nullable = false)
	private String roleId;
	
	@Column(nullable = false)
	private String roleName;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private Set<UserEntity> user;
	
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

	public Set<UserEntity> getUser() {
		return user;
	}

	public void setUser(Set<UserEntity> user) {
		this.user = user;
	}
}
