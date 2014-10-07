/***********************************************************************
 *
 * @file         PK.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月7日
 *
 *
 ***********************************************************************/
package com.mweb.model.plugin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author jet
 *
 */

@Embeddable
public class PK implements Serializable
{
	private String userId;
	private String propertyCode;
	
	@Column(name="USER_ID")
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	@Column(name="PROPERTY_CODE")
	public String getPropertyCode()
	{
		return propertyCode;
	}
	public void setPropertyCode(String propertyCode)
	{
		this.propertyCode = propertyCode;
	}
	
	
	
}
