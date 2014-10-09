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

import java.text.NumberFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import com.mweb.common.util.FormatUtil;
import com.mweb.model.ProgressRateResult;
import com.mweb.service.WatchService;

import static com.mweb.common.constats.Constants.*;

/**
 * @author jet
 *
 */
@Component
public class StepListener implements StepExecutionListener
{
	private static Log log = LogFactory.getLog(StepListener.class);

	public void beforeStep(StepExecution stepExecution)
	{
		// log.info("[Before Step Count]"+ stepExecution.getWriteCount());
	}

	public ExitStatus afterStep(StepExecution stepExecution)
	{
		String taskId = stepExecution.getJobExecution().getId().toString();
		ProgressRateResult result = WatchService.getProgressResult(taskId);
		if (LOAD_DATA_STEP.endsWith(stepExecution.getStepName()))
		{
			result.setTotalCount(result.getTotalCount() * stepExecution.getReadCount());
			log.info("[TOTAL]" + result);
		}
		else
		{
			if (result.getTotalCount() > 0)
			{
				double left = Double.parseDouble(String.valueOf(result.getTotalCount() - stepExecution.getReadCount()));
				double count = left / result.getTotalCount();
				String rate = FormatUtil.formatDouble(count * result.getMaxValue());
				log.info("[LEFT]" + rate + "," + left + "," + count);
				long value = Long.parseLong(rate);
				result.setCurrentValue(value);
			}
		}
		log.info(result);
		stepExecution.setStatus(BatchStatus.COMPLETED);
		return stepExecution.getExitStatus();

	}

}
