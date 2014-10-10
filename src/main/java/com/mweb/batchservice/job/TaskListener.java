/***********************************************************************
 *
 * @file         TaskListener.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月8日
 *
 *
 ***********************************************************************/
package com.mweb.batchservice.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * @author jet
 *
 */

@Component("TaskListener")
public class TaskListener implements ChunkListener
{
	private static Log log = LogFactory.getLog(TaskListener.class);
	
	@BeforeRead
	public void beforeRead()
	{
		log.info("[before read]");
	}
	
	@AfterRead
	public void afterRead()
	{
		log.info("[after read]");
	}
	
	@BeforeWrite
	public void beforeWrite()
	{
		log.info("[before write]");
	}
	
	@AfterWrite
	public void afterWrite()
	{
		log.info("[after write]");
	}
	
	@BeforeProcess
	public void beforeProcess()
	{
		log.info("[before process]");
	}
	
	@AfterProcess
	public void afterProcess()
	{
		log.info("[after process]");
	}
	
	@BeforeStep
	public void beforeStep()
	{
		log.info("[before step]");
	}
	
	@AfterStep
	public void afterStep()
	{
		log.info("[after step]");
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.ChunkListener#beforeChunk(org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public void beforeChunk(ChunkContext context)
	{
		log.info("[before chunk]");
		log.info("[READ=="+context.getStepContext().getStepExecution().getReadCount()+"]");
		log.info("[WRITE=="+context.getStepContext().getStepExecution().getWriteCount()+"]");
		log.info("[JOBID=="+context.getStepContext().getStepExecution().getJobExecution().getJobId()+"]");
		log.info("[STEPNAME=="+context.getStepContext().getStepExecution().getStepName()+"]");
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.ChunkListener#afterChunk(org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public void afterChunk(ChunkContext context)
	{
		// TODO Auto-generated method stub
		log.info("[after chunk]");
		log.info("[READ=="+context.getStepContext().getStepExecution().getReadCount()+"]");
		log.info("[WRITE=="+context.getStepContext().getStepExecution().getWriteCount()+"]");
		log.info("[JOBID=="+context.getStepContext().getStepExecution().getJobExecution().getJobId()+"]");
		log.info("[STEPNAME=="+context.getStepContext().getStepExecution().getStepName()+"]");
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.ChunkListener#afterChunkError(org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public void afterChunkError(ChunkContext context)
	{
		// TODO Auto-generated method stub
		
	}
}
