/***********************************************************************
 *
 * @file         TreeMenuNode.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月22日
 *
 *
 ***********************************************************************/
package com.mweb.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author jet
 *
 */
@Entity
@DiscriminatorValue("LEAF") 
public class TreeMenuNode extends AbstractTreeNode implements Serializable
{
	@Column(name="MENU_URL")
	private String menuUrl;

	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = menuUrl;
	}

}
