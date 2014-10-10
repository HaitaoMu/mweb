package com.mweb.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mweb.batchservice.plugins.SAPJob;
import com.mweb.config.common.ApplicationConfiguration;
import com.mweb.service.WatchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class BatchServiceTest
{

	@Autowired
	SAPJob sapJob;

	@Autowired
	SimpleJobLauncher jobLauncher;

	@Test
	public void testTransferDataJob()
	{

		try
		{
			JobExecution execution = jobLauncher.run(sapJob.dataTransferJob(),
					WatchService.getCurrentParameter());

			System.out.println("[execution]"
					+ execution.getStepExecutions().size());

		} catch (JobExecutionAlreadyRunningException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
