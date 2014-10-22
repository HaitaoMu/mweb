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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author jet
 *
 */
@MappedSuperclass 
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class AbstractTreeEntity implements Serializable
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


	
}
