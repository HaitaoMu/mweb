package com.mweb.service.publisher;

import org.springframework.context.ApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mweb.service.event.NotificationProgressEvent;

@Component
public class NotificationProgressPublisher implements ApplicationEventPublisher
{

	private ApplicationEventPublisher publisher;
	
	private NotificationProgressEvent event;
	
//	@Override
//	public void setApplicationEventPublisher(
//			ApplicationEventPublisher applicationEventPublisher)
//	{
//		this.publisher = applicationEventPublisher;
//	}
//
//	public void notifyProgress()
//	{
//		 event = new NotificationProgressEvent(true,WatchService.getProgressMessage(),this);
//		 publisher.publishEvent(event);
//	}
	

	@Override
	public void publishEvent(ApplicationEvent event)
	{
		// TODO Auto-generated method stub
		
	}
	
}
