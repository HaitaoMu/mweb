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

import java.util.concurrent.atomic.AtomicLong;

import ognl.SetPropertyAccessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

import com.mweb.model.ProgressRateResult;
import com.mweb.service.WatchService;
import static com.mweb.

/**
 * @author jet
 *
 */
@Component
public class StepListener implements StepExecutionListener
{
    private static Log log = LogFactory.getLog(StepListener.class);
	
	private static long STEP_START_TIME = 0;
	
    private static AtomicLong staticsCount = new AtomicLong();
    
	public void beforeStep(StepExecution stepExecution)
	{
		STEP_START_TIME = System.currentTimeMillis();
//		log.info(String.format("[ STEP start at %s]", STEP_START_TIME));
		log.info("[Before Step]"+ stepExecution.toString());
		
		if(LOAD_DATA_STEP == stepExecution.getStepName())
		{
			String taskId = String.valueOf(stepExecution.getJobExecution().getJobId());
			ProgressRateResult result = WatchService.getProgressResult(taskId);
			result.setTotalCount(stepExecution.getReadCount());
		}
	}

	public ExitStatus afterStep(StepExecution stepExecution)
	{
//		log.info(String.format("[ STEP cost %s]", (System.currentTimeMillis() - STEP_START_TIME)));

		stepExecution.setStatus(BatchStatus.COMPLETED);
		log.info("[After Step]"+ stepExecution.toString());
		return stepExecution.getExitStatus();

	}
	
	/**
	 * @return the staticsCount
	 */
	public static Long getStaticsCount()
	{
		return staticsCount.longValue();
	}


}
