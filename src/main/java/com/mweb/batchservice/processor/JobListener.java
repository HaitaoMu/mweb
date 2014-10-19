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
import static com.mweb.common.constats.Constants.PLUGIN_LOCK_TYPE;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.mweb.common.constats.PluginType;
import com.mweb.model.ProgressRateResult;
import com.mweb.service.LockService;
import com.mweb.service.WatchService;
import com.mweb.service.publisher.NotificationProgressPublisher;
import com.mweb.common.util.PluginTypeUtil;

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
	NotificationProgressPublisher publisher;

	@Autowired
	LockService lockService;

	@BeforeJob
	public void beforeJob(JobExecution jobExecution)
	{
		JobParameters parameters = jobExecution.getJobParameters();
		if (null != parameters)
		{
			String type = parameters.getString(PLUGIN_LOCK_TYPE);
			lockService.lockRunningJob(type);

			ProgressRateResult result = new ProgressRateResult();
			result.setTaskId(String.valueOf(jobExecution.getJobId()));
			result.setTitle(jobExecution.getJobInstance().getJobName());
			if (DATA_TRANSFER_JOB.equals(jobExecution.getJobInstance()
					.getJobName()))
			{
				result.setTotalCount(TRANSFER_DATA_JOB_STEPS);
			}
			if (AUTO_DATA_TRANSFER_JOB.equals(jobExecution.getJobInstance()
					.getJobName()))
			{
				result.setTotalCount(AUTO_TRANSFER_DATA_JOB_STEPS);
			}
			WatchService.putTask(result);
			publisher.notifyProgress();
			try
			{
				Thread.sleep(5000L);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@AfterJob
	public void afterJob(JobExecution jobExecution)
	{
		JobParameters param = jobExecution.getJobParameters();
		if (null != param)
		{
			String type = param.getString(PLUGIN_LOCK_TYPE);
			lockService.unlockRunningJob(type);

			String taskId = String.valueOf(jobExecution.getJobId());
			ProgressRateResult result = WatchService.getProgressResult(taskId);
			result.setCurrentValue(result.getMaxValue());
			log.info(result);
			publisher.notifyProgress();
		}
	}

}
