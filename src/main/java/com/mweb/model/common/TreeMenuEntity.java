/***********************************************************************
 *
 * @file         TreeMenuEntity.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月22日
 *
 *
 ***********************************************************************/
package com.mweb.model.common;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author jet
 *
 */
@Entity
@Table(name="TREE_MENU")
public class TreeMenuEntity extends AbstractTreeEntity
{

	@Column(name="MENU_URL")
	private String menuUrl;
	
	@Column(name="MENU_DESCRIPTION")
	private String menuDescription;
	
	@ManyToOne(fetch = FetchType.EAGER,targetEntity=TreeMenuEntity.class)
	@JoinColumn(name = "PARENT_ID")
	private TreeMenuEntity parent;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "parent")
	private Set<TreeMenuEntity> childrens;

	/**
	 * 
	 */
	public TreeMenuEntity(){}
	
	

	/**
	 * @param name
	 * @param menuUrl
	 * @param menuDescription
	 */
	public TreeMenuEntity(String name,String menuUrl, String menuDescription)
	{
		super();
		setName(name);
		this.menuUrl = menuUrl;
		this.menuDescription = menuDescription;
	}



	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = menuUrl;
	}

	public String getMenuDescription()
	{
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription)
	{
		this.menuDescription = menuDescription;
	}

	public TreeMenuEntity getParent()
	{
		return parent;
	}

	public void setParent(TreeMenuEntity parent)
	{
		this.parent = parent;
	}

	public Set<TreeMenuEntity> getChildrens()
	{
		return childrens;
	}

	public void setChildrens(Set<TreeMenuEntity> childrens)
	{
		this.childrens = childrens;
	}
	
	
}
