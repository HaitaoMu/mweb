/***********************************************************************
 *
 * @file         Menu.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月2日
 *
 *
 ***********************************************************************/
package com.mweb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jet
 *
 */
@Entity
@Table(name = "MENU")
public class Menu implements Serializable
{

	private String menuId;

	private String menuName;

	private String menuUrl;

	private String menuDescription;

	@Id
	@Column(name = "MENU_ID", length = 32, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getMenuId()
	{
		return menuId;
	}

	public void setMenuId(String menuId)
	{
		this.menuId = menuId;
	}

	@Column(name="MENU_NAME")
	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	@Column(name="MENU_URL")
	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = menuUrl;
	}

	@Column(name="MENU_DESCRIPTION")
	public String getMenuDescription()
	{
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription)
	{
		this.menuDescription = menuDescription;
	}

}
