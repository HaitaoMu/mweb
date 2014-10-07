/***********************************************************************
 *
 * @file         ProgressRateResult.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月7日
 *
 *
 ***********************************************************************/
package com.mweb.model;

/**
 * @author jet
 *
 */
public class ProgressRateResult
{

	private int minValue;

	private int maxValue;

	private int currentValue;

	private String messsage;

	private String taskId;

	public int getMinValue()
	{
		return minValue;
	}

	public void setMinValue(int minValue)
	{
		this.minValue = minValue;
	}

	public int getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(int maxValue)
	{
		this.maxValue = maxValue;
	}

	public int getCurrentValue()
	{
		return currentValue;
	}

	public void setCurrentValue(int currentValue)
	{
		this.currentValue = currentValue;
	}

	public String getMesssage()
	{
		return messsage;
	}

	public void setMesssage(String messsage)
	{
		this.messsage = messsage;
	}

	public String getTaskId()
	{
		return taskId;
	}

	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

}
