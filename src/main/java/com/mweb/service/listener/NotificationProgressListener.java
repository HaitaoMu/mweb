package com.mweb.service.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
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


	@Override
	public void onApplicationEvent(NotificationProgressEvent event)
	{
//	    log.info("Notification Event Trigger"+event.getNotificationMessage());
//		if(null != event)
//		{
//		   template.convertAndSend("/topic/tasknotification", "Hello");
//		}
		sendMessage();
    }
	
	public void sendMessage()
	{
		log.info("Notification Event Trigger");
		template.convertAndSend("/topic/tasknotification", "Hello");
   }

}
//@formatter:on
