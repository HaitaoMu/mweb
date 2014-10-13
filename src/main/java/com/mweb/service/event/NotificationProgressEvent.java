package com.mweb.service.event;

import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;

//@Component
public class NotificationProgressEvent extends BrokerAvailabilityEvent
{
	
	public NotificationProgressEvent(boolean brokerAvailable, Object source)
	{
		super(brokerAvailable, source);
	}
	
	public NotificationProgressEvent(boolean brokerAvailable,String message, Object source)
	{
		super(brokerAvailable, source);
		setNotificationMessage(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1758179089602240615L;

	private String notificationMessage;

	

	public String getNotificationMessage()
	{
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage)
	{
		this.notificationMessage = notificationMessage;
	}

}
