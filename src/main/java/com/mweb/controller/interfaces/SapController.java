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

import static com.mweb.common.constats.Constants.FAIL;
import static com.mweb.common.constats.Constants.SUCCESS;
import static com.mweb.common.constats.Constants.RUN_AGAIN;
import static com.mweb.common.constats.Constants.PLUGIN_LOCK_TYPE;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mweb.batchservice.plugins.SAPJob;
import com.mweb.common.constats.LockStatus;
import com.mweb.common.constats.MessageInfo;
import com.mweb.common.constats.PluginType;
import com.mweb.controller.AbstractController;
import com.mweb.model.BatchLock;
import com.mweb.model.PageResult;
import com.mweb.model.Result;
import com.mweb.repository.BatchLockService;
import com.mweb.repository.plugin.SAPService;
import com.mweb.service.LockService;
import com.mweb.service.WatchService;

/**
 * @author jet
 *
 */

@Controller
public class SapController extends AbstractController
{

	@Autowired
	SAPJob job;

	@Autowired
	SAPService sapService;

	@Autowired
	LockService lockService;

	@Autowired
	SimpleJobLauncher jobLauncher;

	private static final String LOCKTYPE = "LOCK";

	@RequestMapping("/sapIndex")
	public String Sap()
	{
		return "sapInterface";
	}

	@RequestMapping(value = "/pageSapEntity")
	@ResponseBody
	public PageResult pageSapEntity(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "order", defaultValue = "asc") String asc)
	{
		PageResult page = sapService.findByPage(pageNum, pageSize);
		return page;
	}

	@RequestMapping(value = "/sapImportData/{run}", method =
	{ RequestMethod.POST })
	@ResponseBody
	public Result importDataImmediate(@PathVariable(value = "run") String run)
	{
		try
		{
			if ((null!=run && run.equals(RUN_AGAIN))||lockService.checkRunningJob(PluginType.SAP.toString()))
			{
				Map params = new HashMap<String, String>();
				params.put(PLUGIN_LOCK_TYPE, PluginType.SAP.toString());
				jobLauncher.run(job.dataTransferJob(), WatchService.getCurrentParameter(params));
				return new Result(SUCCESS, null);
			}
			else
			{
				return new Result(SUCCESS, getMessage(MessageInfo.ALREADY_RUNNING));
			}
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
		return new Result(FAIL, null);
	}

}
