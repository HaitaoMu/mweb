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

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jet
 *
 */
@Entity
@Table(name = "USER_INFO")
public class UserInfo {
	private String userId;

	private String userName;

	private String userPassword;

	private String userDescription;

	private Set<UserWithRole> roleCollection;

	@Id
	@Column(name = "USER_ID", length = 32, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_PASSWORD")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "USER_DESCRIPTION")
	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	@OneToMany(mappedBy="role")
	public Set<UserWithRole> getRoleCollection() {
		return roleCollection;
	}

	public void setRoleCollection(Set<UserWithRole> roleCollection) {
		this.roleCollection = roleCollection;
	}


	
}
