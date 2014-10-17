package com.mweb.service.listener;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.mweb.service.event.NotificationProgressEvent;

@Component
//@formatter:off
public class NotificationProgressListener implements ApplicationListener<NotificationProgressEvent>
{

	private static Log log = LogFactory.getLog(NotificationProgressListener.class);

	@Autowired
	private  SimpMessagingTemplate template;

	private final MessageSendingOperations<String> messagingTemplate;

	private AtomicBoolean brokerAvailable = new AtomicBoolean();
	
    @Autowired
	public NotificationProgressListener (final MessageSendingOperations<String> messagingTemplate)
    {
	        this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void onApplicationEvent(NotificationProgressEvent event)
	{
//	    log.info("Notification Event Trigger"+event.getNotificationMessage());
//		if(null != event)
//		{
//		   template.convertAndSend("/topic/tasknotification", "Hello");
//		}
		this.brokerAvailable.set(event.isBrokerAvailable());
		sendMessage();
    }
	
//	@Scheduled(fixedDelay=1000)
	public void sendMessage()
	{
		if(this.brokerAvailable.get())
		{
		   log.info("Notification Event Trigger");
		   messagingTemplate.convertAndSend("/topic/tasknotification", "Hello");
		}
   }

}
//@formatter:on
