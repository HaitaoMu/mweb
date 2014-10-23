/***********************************************************************
 *
 * @file         AbstractTree.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月22日
 *
 *
 ***********************************************************************/
package com.mweb.model.common;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author jet
 *
 */
@Entity
@Table(name="ABSTRACT_TREE_NODE")
@DiscriminatorColumn(name="TYPE",discriminatorType=DiscriminatorType.STRING) 
@DiscriminatorValue("ROOT") 
public class AbstractTreeNode implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7145507881241046770L;

	@Id 
	@Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;

	@Column(name="NAME")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private AbstractTreeNode parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private Set<AbstractTreeNode> childrens;

	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public AbstractTreeNode getParent()
	{
		return parent;
	}

	public void setParent(AbstractTreeNode parent)
	{
		this.parent = parent;
	}

	public Set<AbstractTreeNode> getChildrens()
	{
		return childrens;
	}

	public void setChildrens(Set<AbstractTreeNode> childrens)
	{
		this.childrens = childrens;
	}
}
