package com.mweb.service.publisher;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.mweb.service.WatchService;
import com.mweb.service.event.NotificationProgressEvent;

//ApplicationEventPublisherAware

@Component
public class NotificationProgressPublisher implements ApplicationContextAware
{

//	private ApplicationEventPublisher publisher;
	
	private NotificationProgressEvent event;
	
	
//	@Override
//	public void setApplicationEventPublisher(
//			ApplicationEventPublisher applicationEventPublisher)
//	{
//		this.publisher = applicationEventPublisher;
//	}

	public void notifyProgress()
	{
		 event = new NotificationProgressEvent(true,WatchService.getProgressMessage(),this);
		 applicationContext.publishEvent(event);
	}
	
	private ApplicationContext applicationContext;

	@Autowired
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	public NotificationProgressEvent getEvent()
	{
		return event;
	}

	public void setEvent(NotificationProgressEvent event)
	{
		this.event = event;
	}
	
	
}
