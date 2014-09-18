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

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author jet
 *
 */

@Entity
@Table(name="DBConfig")
public class DBConfig  implements Serializable
{
	
	private Long id;
	private String url;
	private String driver;
	private String username;
	private String password;
	private DBType dbType;
	
	private String dbName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="id_generator")
	@SequenceGenerator(name="id_generator",sequenceName="DB_ID_SEQ")
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getDriver()
	{
		return driver;
	}
	public void setDriver(String driver)
	{
		this.driver = driver;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getDbName()
	{
		return dbName;
	}
	public void setDbName(String dbName)
	{
		this.dbName = dbName;
	}
	
	@Enumerated(EnumType.STRING)
	public DBType getDbType()
	{
		return dbType;
	}
	public void setDbType(DBType dbType)
	{
		this.dbType = dbType;
	}
	
	
	
	
	
}
