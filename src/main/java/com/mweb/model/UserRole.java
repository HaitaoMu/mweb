package com.mweb.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE")
public class UserRole {

	private String roleId;
	private String roleName;
	private String roleDescription;
	private Set<UserInfo> userCollection;

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

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.LAZY,mappedBy="roleCollection")
	public Set<UserInfo> getUserCollection() {
		return userCollection;
	}

	public void setUserCollection(Set<UserInfo> userCollection) {
		this.userCollection = userCollection;
	}

}
