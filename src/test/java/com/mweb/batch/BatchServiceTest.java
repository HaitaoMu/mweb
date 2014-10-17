package com.mweb.batch;

import static com.mweb.common.constats.Constants.PLUGIN_LOCK_TYPE;

import java.util.HashMap;
import java.util.Map;

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
import com.mweb.common.constats.PluginType;
import com.mweb.config.common.ApplicationConfiguration;
import com.mweb.service.WatchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class BatchServiceTest
{

	@Autowired
	SAPJob sapJob;

	@Autowired
	WatchService watchService;

	@Autowired
	SimpleJobLauncher jobLauncher;

	@Test
	public void testTransferDataJob()
	{

		try
		{
			Map params = new HashMap<String, Object>();
			params.put(PLUGIN_LOCK_TYPE, PluginType.SAP);
			JobExecution execution = jobLauncher.run(sapJob.dataTransferJob(),
					watchService.getCurrentParameter(params));

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
