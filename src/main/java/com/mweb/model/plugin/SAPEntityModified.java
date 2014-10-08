/***********************************************************************
 *
 * @file         SAPEntity.java
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
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author jet
 *
 */
@Entity
public class SAPEntityModified implements Serializable
{
	@EmbeddedId
	PK pk;

	
	private double price;

	private double volume;

	private double amount;

	@JsonIgnore
	public PK getPk()
	{
		return pk;
	}

	public void setPk(PK pk)
	{
		this.pk = pk;
	}

	@Column(name="PRICE")
	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	@Column(name="VOLUME")
	public double getVolume()
	{
		return volume;
	}

	public void setVolume(double volume)
	{
		this.volume = volume;
	}

	@Column(name="AMOUNT")
	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	@Transient
	public String getUserId()
	{
			return pk.getUserId();
	}
	
	@Transient
	public String getPropertyCode()
	{
			return pk.getPropertyCode();
	}
}
