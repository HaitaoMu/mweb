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

	private int minValue;

	private int maxValue;

	private int currentValue;

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

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
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

}
