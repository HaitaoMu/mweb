/***********************************************************************
 *
 * @file        JobListener.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin 
 * @create-time 2014年9月16日
 *
 *
 ***********************************************************************/
package com.mweb.batchservice.processor;

import static com.mweb.common.constats.Constants.AUTO_DATA_TRANSFER_JOB;
import static com.mweb.common.constats.Constants.DATA_TRANSFER_JOB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.messaging.simp.SimpMessagingTemplate;
=======
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
>>>>>>> 1e68479322f7c85e1e0ee031a0ce3b9276be1366
import org.springframework.stereotype.Component;

import com.mweb.controller.messages.TaskMessageService;
import com.mweb.model.ProgressRateResult;
import com.mweb.service.WatchService;
import com.mweb.service.event.NotificationProgressEvent;
import com.mweb.service.publisher.NotificationProgressPublisher;

/**
 * @author jet
 *
 */
@Component
public class JobListener implements JobExecutionListener
{
	private static Log log = LogFactory.getLog(JobListener.class);

	private static final Long TRANSFER_DATA_JOB_STEPS = 2L;

	private static final Long AUTO_TRANSFER_DATA_JOB_STEPS = 3L;
	
	@Autowired
<<<<<<< HEAD
	WatchService watchService;

=======
	NotificationProgressPublisher publisher;
	
>>>>>>> 1e68479322f7c85e1e0ee031a0ce3b9276be1366
	@BeforeJob
	public void beforeJob(JobExecution jobExecution)
	{
		ProgressRateResult result = new ProgressRateResult();
		result.setTaskId(String.valueOf(jobExecution.getJobId()));
		result.setTitle(jobExecution.getJobInstance().getJobName());
		if (DATA_TRANSFER_JOB.equals(jobExecution.getJobInstance().getJobName()))
		{
			result.setTotalCount(TRANSFER_DATA_JOB_STEPS);
		}
		if (AUTO_DATA_TRANSFER_JOB.equals(jobExecution.getJobInstance().getJobName()))
		{
			result.setTotalCount(AUTO_TRANSFER_DATA_JOB_STEPS);
		}
<<<<<<< HEAD
		watchService.putTask(result);
		watchService.sendMessage();
		
=======
		WatchService.putTask(result);
		
		publisher.notifyProgress();
>>>>>>> 1e68479322f7c85e1e0ee031a0ce3b9276be1366
	}

	@AfterJob
	public void afterJob(JobExecution jobExecution)
	{
		String taskId = String.valueOf(jobExecution.getJobId());
		ProgressRateResult result = watchService.getProgressResult(taskId);
		result.setCurrentValue(result.getMaxValue());
		log.info(result);
<<<<<<< HEAD
		watchService.sendMessage();
=======
		
		publisher.notifyProgress();
>>>>>>> 1e68479322f7c85e1e0ee031a0ce3b9276be1366
	}

}
