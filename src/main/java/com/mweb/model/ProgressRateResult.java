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

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * @author jet
 *
 */
public class ProgressRateResult implements Message {

	/**
	 * 
	 */
	private static final int MAX = 100;
	private static final int MIN = 0;
	public ProgressRateResult()
	{
		setMinValue(MIN);
		setMaxValue(MAX);
	}
	
	private int minValue;

	private int maxValue;

	private long currentValue;
	
	private String title;

	private String messsage;

	private String taskId;

	private long totalCount;

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public long getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(long currentValue) {
		this.currentValue = currentValue;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public long getTotalCount() 
	{
		return totalCount;
	}

	public void setTotalCount(long totalCount) 
	{
		this.totalCount = totalCount;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.messaging.Message#getPayload()
	 */
	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.messaging.Message#getHeaders()
	 */
	@Override
	public MessageHeaders getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return String.format("[TaskId=%s,CurrentValue=%d,Message=%s]", getTaskId(),getCurrentValue(),getMesssage());
	}
}
