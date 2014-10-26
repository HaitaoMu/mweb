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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import static com.mweb.common.constats.Constants.WEB_ROOT;

/**
 * @author jet
 *
 */
@Entity
@Table(name = "TREE_MENU")
public class TreeMenuEntity extends AbstractTreeEntity implements Comparable<TreeMenuEntity>
{


	@Column(name = "MENU_URL")
	private String menuUrl;

	@Column(name = "MENU_ICON")
	private String menuIcon;

	@Column(name = "MENU_DESCRIPTION")
	private String menuDescription;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = TreeMenuEntity.class)
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
	private TreeMenuEntity parent;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "parent")
	private Set<TreeMenuEntity> childrens;

	/**
	 * 
	 */
	public TreeMenuEntity()
	{
	}

	/**
	 * @param name
	 * @param menuUrl
	 * @param menuDescription
	 */
	public TreeMenuEntity(String name, String menuUrl, String menuIcon)
	{
		super();
		setName(name);
		this.menuUrl = menuUrl;
		this.menuIcon = menuIcon;
	}

	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = WEB_ROOT+menuUrl;
	}

	public String getMenuIcon()
	{
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon)
	{
		this.menuIcon = menuIcon;
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

	public void parseTreeMenu(TreeMenuEntity menu)
	{
		if (null != menu)
		{
			for (TreeMenuEntity entity : menu.getChildrens())
			{
				parseTreeMenu(entity);
			}
		}
	}

	public String createTreeMenu(TreeMenuEntity menu)
	{
//		@formatter:off
		StringBuffer buffer = new StringBuffer();
		if (null != menu)
		{
			List<TreeMenuEntity> entities = new ArrayList(menu.getChildrens());
			Collections.sort(entities);
			for (TreeMenuEntity entity :  entities)
			{
				boolean isLeaf = (null == entity.getChildrens()||0 == entity.getChildrens().size()) ? true : false; 
				if(isLeaf)
				{
					buffer.append(createLinkMenu(entity));
				}
				else
				{
					buffer.append("<li>");
					buffer.append("<a href='"+entity.menuUrl+"'><i class='"+entity.getMenuIcon()+"'></i>"+entity.getName()+"</span><span class='fa arrow'></span></a>");
					buffer.append("<ul class='nav nav-second-level'>");
					buffer.append(createTreeMenu(entity));
					buffer.append("</ul>");
					buffer.append("</li>");
				}
			}
		}
		
		return buffer.toString();


	}
	
	private String createLinkMenu(TreeMenuEntity menu)
	{
		StringBuffer buffer = new StringBuffer();
		if (null!=menu)
		{
			buffer.append("<li>");
			buffer.append("<a href='" + menu.menuUrl + "'><i class='"+menu.getMenuIcon() + "'></i>" + menu.getName()+ "</span></a>");
			buffer.append("</li>");
		}
		return buffer.toString();
	}
	
	
	@Override
	public String toString()
	{
		return String.format("[name=%s,url=%s,icon=%s,description=%s]", getName(),getMenuUrl(),getMenuIcon(),getMenuDescription());
	}

	@Override
	public int compareTo(TreeMenuEntity menu)
	{
		if(null == menu)
		{
			return EQUAL;
		}
		if(getId() > menu.getId())
		{
			return LARGER;
		}
		if(getId() == menu.getId())
		{
			return EQUAL;
		}
		if(getId() < menu.getId())
		{
			return LESS;
		}
		return EQUAL;
	}
//	@formatter:on

}
