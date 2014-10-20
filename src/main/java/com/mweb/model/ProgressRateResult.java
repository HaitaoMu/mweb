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
	private static final Long MAX = 100L;
	private static final Long MIN = 0L;
	public ProgressRateResult()
	{
		setMinValue(MIN);
		setMaxValue(MAX);
		setCurrentValue(MIN);
		setTotalCount(MAX);
	}
	
	private Long minValue;

	private Long maxValue;

	private Long currentValue;
	
	private String title;

	private String messsage;

	private String taskId;

	private Long totalCount;
	
	private String type;

	public Long getMinValue() {
		return minValue;
	}

	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}

	public Long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}

	public Long getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Long currentValue) {
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

	public Long getTotalCount() 
	{
		return totalCount;
	}

	public void setTotalCount(Long totalCount) 
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
	

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("[TaskId=%s,CurrentValue=%d,Message=%s]", getTaskId(),getCurrentValue(),getMesssage());
	}
}
