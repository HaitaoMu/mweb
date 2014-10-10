package com.mweb.controller.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mweb.config.common.ApplicationConfiguration;
import com.mweb.config.security.SecurityConfiguration;
import com.mweb.model.ProgressRateResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TaskMessageServiceTest
{
	private Log log = LogFactory.getLog(TaskMessageServiceTest.class);
	
	@Autowired
	SimpMessagingTemplate template;

	@Test
	public void testTaskNotification()
	{
		 log.info("[Send Notification]");
		 ProgressRateResult progressRateResult = new ProgressRateResult();
//		 progressRateResult.setCurrentValue(progressRate.getAndIncrement());
//		 progressRateResult.setTitle("SAP IMPORT");
//		 progressRateResult.setMesssage(getProgressMessage(progressRateResult));
		 template.convertAndSend("/topic/tasknotification",progressRateResult);
	}
}
