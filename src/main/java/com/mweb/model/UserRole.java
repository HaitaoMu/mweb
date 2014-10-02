package com.mweb.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER_ROLE")
public class UserRole implements Serializable{

	private String roleId;
	private String roleName;
	private String roleDescription;
//	private Set<UserWithRole> userCollection;
	private Set<UserInfo> users;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name="ROLE_ID",length=32, unique = true)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name="ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name="ROLE_DESCRIPTION")
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	//formatter:off
	@ManyToMany(targetEntity=UserInfo.class,
			cascade={CascadeType.ALL},
			mappedBy="roles",fetch=FetchType.LAZY)
	//formatter:on
	public Set<UserInfo> getUsers()
	{
		return users;
	}

	public void setUsers(Set<UserInfo> users)
	{
		this.users = users;
	}

//	@OneToMany(mappedBy="user")
//	public Set<UserWithRole> getUserCollection() {
//		return userCollection;
//	}
//
//	public void setUserCollection(Set<UserWithRole> userCollection) {
//		this.userCollection = userCollection;
//	}

	@Override
	public String toString()
	{
		return String.format("[roleId=%s,roleName=%s]", getRoleId(),getRoleName());
	}
	
}
