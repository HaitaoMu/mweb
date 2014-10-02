/***********************************************************************
 *
 * @file         UserDetails.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年9月29日
 *
 *
 ***********************************************************************/
package com.mweb.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author jet
 *
 */
@Entity
@Table(name = "USER_INFO")
public class UserInfo implements Serializable
{
	private String userId;

	private String userName;

	private String userPassword;

	private String userDescription;

	// private Set<UserWithRole> roleCollection;

	private Set<UserRole> roles;

	@Id
	@Column(name = "USER_ID", length = 32, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	@Column(name = "USER_NAME")
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Column(name = "USER_PASSWORD")
	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	@Column(name = "USER_DESCRIPTION")
	public String getUserDescription()
	{
		return userDescription;
	}

	public void setUserDescription(String userDescription)
	{
		this.userDescription = userDescription;
	}

	//@formatter:off
	@ManyToMany(targetEntity = UserRole.class,
			cascade ={ CascadeType.ALL },
			fetch=FetchType.EAGER)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	@JoinTable(name="USER_WITH_ROLE",
			joinColumns={@JoinColumn(name="USER_ID")},
			inverseJoinColumns={@JoinColumn(name="ROLE_ID")})
	//@formatter:on
	public Set<UserRole> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<UserRole> roles)
	{
		this.roles = roles;
	}

	// @OneToMany(mappedBy="role")
	// public Set<UserWithRole> getRoleCollection() {
	// return roleCollection;
	// }
	//
	// public void setRoleCollection(Set<UserWithRole> roleCollection) {
	// this.roleCollection = roleCollection;
	// }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("[userId=%s] [userName=%s] and [userDescription=%s]",getUserId(), getUserName(),getUserDescription());
	}
}
