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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.stereotype.Component;

import com.mweb.model.ProgressRateResult;
import com.mweb.service.WatchService;

/**
 * @author jet
 *
 */
@Component
public class JobListener 
{
	private static Log log = LogFactory.getLog(JobListener.class);

	private static long STEP_START_TIME = 0;
	
	
	@BeforeRead
	public void beforeRead()
	{
		
	}
	
	@BeforeWrite
	public void beforeWrite()
	{
		
	}
	
	@AfterRead
	public void afterRead()
	{
		
	}
	
	@AfterWrite
	public void afterWrite()
	{
		
	}
	
	
    @BeforeJob
    public void beforeJob(JobExecution jobExecution){
    	ProgressRateResult result = new ProgressRateResult();
    	result.setTaskId(String.valueOf(jobExecution.getJobId()));
    	WatchService.putTask(result);
//        log.info("*********Before Job*********");
//        STEP_START_TIME = System.currentTimeMillis();
//        log.info(String.format("[Job start at %s]", STEP_START_TIME));
//        log.info("[Job Count]"+jobExecution.getJobId());
    }


    @AfterJob
    public void afterJob(JobExecution jobExecution){
//        log.info("*********Afetr Job*********");
//		log.info(String.format("[Job cost %s]", (System.currentTimeMillis() - STEP_START_TIME)));
    }
}
