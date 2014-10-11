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
import org.springframework.stereotype.Component;

import com.mweb.controller.messages.TaskMessageService;
import com.mweb.model.ProgressRateResult;
import com.mweb.service.WatchService;

/**
 * @author jet
 *
 */
@Component
public class JobListener implements JobExecutionListener
{
	private static Log log = LogFactory.getLog(JobListener.class);

	private static final int TRANSFER_DATA_JOB_STEPS = 2;

	private static final int AUTO_TRANSFER_DATA_JOB_STEPS = 3;

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
		WatchService.putTask(result);
	}

	@AfterJob
	public void afterJob(JobExecution jobExecution)
	{
		String taskId = String.valueOf(jobExecution.getJobId());
		ProgressRateResult result = WatchService.getProgressResult(taskId);
		result.setCurrentValue(result.getMaxValue());
		log.info(result);
	}
}
