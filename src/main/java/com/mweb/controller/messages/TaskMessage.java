/***********************************************************************
 *
 * @file         TaskMessage.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月7日
 *
 *
 ***********************************************************************/
package com.mweb.controller.messages;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jet
 *
 */
@Controller
public class TaskMessage
{
	@Autowired
	private SimpMessagingTemplate template;
	
	private AtomicInteger progressRate = new AtomicInteger();

	@MessageMapping("/tasknotification")
	@Scheduled(fixedDelay = 3000)
	public void sendMessage()
	{
		template.convertAndSend("/topic/tasknotification", getProgressRate());
	}
	
	public int getProgressRate()
	{
		return progressRate.getAndIncrement();
	}
}
