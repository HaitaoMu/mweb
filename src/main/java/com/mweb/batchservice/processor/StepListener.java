/***********************************************************************
 *
 * @file        StepListener.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin 
 * @create-time 2014年9月16日
 *
 *
 ***********************************************************************/
package com.mweb.batchservice.processor;

import static com.mweb.common.constats.Constants.LOAD_DATA_STEP;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mweb.common.util.FormatUtil;
import com.mweb.model.ProgressRateResult;
import com.mweb.service.WatchService;
import com.mweb.service.event.NotificationProgressEvent;
import com.mweb.service.publisher.NotificationProgressPublisher;

/**
 * @author jet
 *
 */
@Component
public class StepListener implements StepExecutionListener
{
	private static Log log = LogFactory.getLog(StepListener.class);

	private NotificationProgressPublisher publisher;
	
	public void beforeStep(StepExecution stepExecution)
	{
		log.info("[Before Step]"+ stepExecution.toString());
	}

	public ExitStatus afterStep(StepExecution stepExecution)
	{

		log.info("[After Step]"+ stepExecution.toString());
	
		String taskId = stepExecution.getJobExecution().getId().toString();
		ProgressRateResult result = WatchService.getProgressResult(taskId);
		if (LOAD_DATA_STEP.endsWith(stepExecution.getStepName()))
		{
			result.setTotalCount(result.getTotalCount() * stepExecution.getReadCount());
		}
		else
		{
			if (result.getTotalCount() > 0)
			{
				int processCount = stepExecution.getWriteCount() 
									+ stepExecution.getFilterCount() 
									+ stepExecution.getProcessSkipCount() 
									+ stepExecution.getWriteSkipCount();
				
				double left = Double.parseDouble(String.valueOf(result.getTotalCount() - processCount ));
				double count = left / result.getTotalCount();
				String rate = FormatUtil.formatDouble(count * result.getMaxValue());
				long value = Long.parseLong(rate);
				result.setCurrentValue(value);
			}
		}
		
//		publisher.notifyProgress();
		stepExecution.setStatus(BatchStatus.COMPLETED);
		return stepExecution.getExitStatus();

	}

}
