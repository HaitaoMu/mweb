/***********************************************************************
 *
 * @file         SapController.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月7日
 *
 *
 ***********************************************************************/
package com.mweb.controller.interfaces;

import static com.mweb.common.constats.Constants.SUCCESS;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mweb.batchservice.plugins.SAPJob;
import com.mweb.model.PageResult;
import com.mweb.repository.plugin.SAPService;
import com.mweb.service.WatchService;

/**
 * @author jet
 *
 */

@Controller
public class SapController
{
	
	@Autowired
	SAPJob job;
	
	@Autowired
	SAPService sapService;

	@Autowired
	SimpleJobLauncher jobLauncher;

	@RequestMapping("/sapIndex")
	public String Sap()
	{
		return "sapInterface";
	}
	
	@RequestMapping(value = "/pageSapEntity")
	@ResponseBody
	public PageResult pageSapEntity(
			@RequestParam(value = "pageNum",defaultValue="1") int pageNum,
			@RequestParam(value = "pageSize",defaultValue="10") int pageSize)
	{
		PageResult page = sapService.findByPage(pageNum, pageSize);
		return page;
	}
	
	@RequestMapping(value="/sapImportData")
	@ResponseBody
	public String importDataImmediate()
	{
		try
		{
			jobLauncher.run(job.dataTransferJob(), WatchService.getCurrentParameter());
		}
		catch (JobExecutionAlreadyRunningException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JobRestartException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JobInstanceAlreadyCompleteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JobParametersInvalidException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
