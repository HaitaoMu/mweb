package com.mweb.service.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.mweb.service.WatchService;
import com.mweb.service.event.NotificationProgressEvent;

@Component
public class NotificationProgressPublisher implements ApplicationEventPublisherAware
{

	private ApplicationEventPublisher publisher;
	
	private NotificationProgressEvent event;
	
	@Override
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher)
	{
		this.publisher = applicationEventPublisher;
	}

	public void notifyProgress()
	{
		 event = new NotificationProgressEvent(true,WatchService.getProgressMessage(),this);
		 publisher.publishEvent(event);
	}
	
}
