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

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.mweb.model.ProgressRateResult;
import com.mweb.service.WatchService;
import static com.mweb.common.constats.Constants.*;

/**
 * @author jet
 *
 */
@Controller
public class TaskMessageService
{
	@Autowired
	private  SimpMessagingTemplate template;

	private static AtomicInteger progressRate = new AtomicInteger();

	private static Log log = LogFactory.getLog(TaskMessageService.class);

//	@MessageMapping("/tasknotification")
//	@Scheduled(fixedDelay=500)
	public synchronized void sendMessage()
	{
		log.info("Get Task Notification Request");
//		String message = getProgressMessage();
//		if (null != message && message.length() > 0)
//		{
//			template.convertAndSend("/topic/tasknotification", message);
//		}
		//cleanProgressMessage();
	}
	
	
}
