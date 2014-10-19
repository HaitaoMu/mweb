/***********************************************************************
 *
 * @file         Result.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月19日
 *
 *
 ***********************************************************************/
package com.mweb.model;

/**
 * @author jet
 *
 */
public class Result
{

	private String status;
	private String message;
	
	/**
	 * @param status
	 * @param message
	 */
	public Result(String status, String message)
	{
		super();
		this.status = status;
		this.message = message;
	}
	
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	

}
