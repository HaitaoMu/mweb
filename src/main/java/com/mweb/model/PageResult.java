/***********************************************************************
 *
 * @file         PageResult.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月7日
 *
 *
 ***********************************************************************/
package com.mweb.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author jet
 *
 */
public class PageResult<T extends Serializable>
{
	private List<T> rows;
	private long total;
	
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<T> rows)
	{
		this.rows = rows;
	}
	
	/**
	 * @param total the total to set
	 */
	public void setTotal(long total)
	{
		this.total = total;
	}
	
	/**
	 * @return the rows
	 */
	public List<T> getRows()
	{
		return rows;
	}
	
	/**
	 * @return the total
	 */
	public long getTotal()
	{
		return total;
	}
}
