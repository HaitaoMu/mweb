package com.mweb.service.publisher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mweb.service.WatchService;
import com.mweb.service.event.NotificationProgressEvent;

//ApplicationEventPublisherAware

@Component
//public class NotificationProgressPublisher implements ApplicationEventPublisher
public class NotificationProgressPublisher implements ApplicationEventPublisherAware
//public class NotificationProgressPublisher implements ApplicationContextAware
//public class NotificationProgressPublisher implements ApplicationContextAware
//public class NotificationProgressPublisher implements ApplicationEventPublisher
{

//	private ApplicationContext applicationContext; 
	
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
//	
//
//	@Override
//	public void publishEvent(ApplicationEvent event)
//	{
//		 event = new NotificationProgressEvent(true,WatchService.getProgressMessage(),this);
//	}

//	@Override
//	@Autowired
//	public void setApplicationContext(ApplicationContext applicationContext)
//			throws BeansException
//	{
//		this.applicationContext = applicationContext;
	
//	@Override
//	public void setApplicationEventPublisher(
//			ApplicationEventPublisher applicationEventPublisher)
//	{
//		this.publisher = applicationEventPublisher;
//	}

	public void notifyProgress()
	{
		 event = new NotificationProgressEvent(true,WatchService.getProgressMessage(),this);
		 publisher.publishEvent(event);
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationEventPublisher#publishEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	@Autowired
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher)
	{
		this.publisher = publisher;
		notifyProgress();
	}
	

	
	
}
