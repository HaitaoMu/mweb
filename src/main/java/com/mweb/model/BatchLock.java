/***********************************************************************
 *
 * 
 *
 * @file        DBConfig.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月13日
 *
 *
 ***********************************************************************/
package com.mweb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.mweb.common.constats.DBType;
import com.mweb.common.constats.LockStatus;
import com.mweb.common.constats.PluginType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author jet
 *
 */

@Entity
@Table(name = "BATCH_LOCK",uniqueConstraints = {@UniqueConstraint(columnNames={"LOCKTYPE"})} )
public class BatchLock implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7018291961359050691L;
	private Long id;
	private String lockName;
	private String lockDescription;
	private PluginType lockType;
	private LockStatus lockStatus;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "LOCK_ID_SEQ")
	@JsonIgnore
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getLockName()
	{
		return lockName;
	}

	public void setLockName(String lockName)
	{
		this.lockName = lockName;
	}

	public String getLockDescription()
	{
		return lockDescription;
	}

	public void setLockDescription(String lockDescription)
	{
		this.lockDescription = lockDescription;
	}

	@Column(name="LOCKTYPE")
	@Enumerated(EnumType.STRING)
	public PluginType getLockType()
	{
		return lockType;
	}

	public void setLockType(PluginType lockType)
	{
		this.lockType = lockType;
	}


	@Column(name="LOCKSTATUS")
	@Enumerated(EnumType.STRING)
	public LockStatus getLockStatus()
	{
		return lockStatus;
	}

	public void setLockStatus(LockStatus lockStatus)
	{
		this.lockStatus = lockStatus;
	}

}
