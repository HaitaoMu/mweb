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
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

/**
 * @author jet
 *
 */
@Component
public class JobListener implements JobExecutionListener
{
	private static Log log = LogFactory.getLog(JobListener.class);

	private static long STEP_START_TIME = 0;
	
    @BeforeJob
    public void beforeJob(JobExecution jobExecution){
        log.info("*********Before Job*********");
        STEP_START_TIME = System.currentTimeMillis();
        log.info(String.format("[Job start at %s]", STEP_START_TIME));
    }


    @AfterJob
    public void afterJob(JobExecution jobExecution){
        log.info("*********Afetr Job*********");
        log.info(String.format("[Job end at %s]", System.currentTimeMillis()));
		log.info(String.format("[Job cost %s]", (System.currentTimeMillis() - STEP_START_TIME)));
    }
}
