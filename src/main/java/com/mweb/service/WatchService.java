package com.mweb.service;

import static com.mweb.common.constats.Constants.CURRENT_TASK_NO;
import static com.mweb.common.constats.Constants.MAX_PROCESS_VALUE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Service;

import com.mweb.model.ProgressRateResult;

@Service
public class WatchService 
{
	private static ConcurrentHashMap<String,ProgressRateResult> taskMap = new ConcurrentHashMap<String,ProgressRateResult>();
	private static final AtomicLong atomicLong = new AtomicLong();
	
	public static void putTask(ProgressRateResult result)
	{
		taskMap.put(result.getTaskId(),result);
	}
	
	public static ProgressRateResult getProgressResult(String taskId)
	{
		return taskMap.get(taskId);
	}
	
	public static void removeTask(String taskId){
		 taskMap.remove(taskId);
	}
	
	public synchronized static List<ProgressRateResult> getTaskList()
	{
		List<ProgressRateResult> taskList = new ArrayList<ProgressRateResult>();
		for(Map.Entry<String,ProgressRateResult> e: taskMap.entrySet() )
		{
			taskList.add(e.getValue());
		}
		return taskList;
	}
	
	private static synchronized void cleanProgressMessage()
	{
		List<ProgressRateResult> results = WatchService.getTaskList();
		for (ProgressRateResult progressRateResult : results)
		{
			if(MAX_PROCESS_VALUE == progressRateResult.getCurrentValue())
			{
				removeTask(progressRateResult.getTaskId());
			}
		}
	}

	public static synchronized String getProgressMessage()
	{
		StringBuilder builder = new StringBuilder();
		List<ProgressRateResult> results = getTaskList();
		for (ProgressRateResult progressRateResult : results)
		{
			builder.append(getProgressItem(progressRateResult));
		}
		if (null != results && results.size() > 0)
		{
			builder.append(getDetails());
		}
		return builder.toString();
	}

	private static synchronized String getDetails()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("<li>");
		builder.append("<a class='text-center' href='#'>");
		builder.append(" <strong>See All Tasks</strong>");
		builder.append("  <i class='fa fa-angle-right'></i>");
		builder.append("</a>");
		builder.append("</li>");
		return builder.toString();
	}

	private static synchronized String getProgressItem(ProgressRateResult result)
	{
		String message = String.format("%d%% Complete",
				result.getCurrentValue());
		StringBuilder builder = new StringBuilder();
		builder.append("<li>");
		builder.append(" <a href='#'>");
		builder.append(" <div> ");
		builder.append(" <p> ");
		builder.append(" <strong>" + result.getTitle() + "-"
				+ result.getTaskId() + "</strong>");
		builder.append(" <span class='pull-right text-muted'>" + message
				+ "</span>");
		builder.append(" <div class='progress progress-striped active'>");
		builder.append(" <div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='"
				+ result.getCurrentValue()
				+ "' aria-valuemin='0' aria-valuemax='"
				+ result.getMaxValue()
				+ "' style='width:" + result.getCurrentValue() + "%'>");
		builder.append(" <span class='sr-only'>" + message + "</span>");
		builder.append(" </div>");
		builder.append(" </div>");
		builder.append(" </div>");
		builder.append(" </a>");
		builder.append(" </li>");
		builder.append(" <li class='divider'></li>");
		return builder.toString();
	}
	
	
	public synchronized static JobParameters getCurrentParameter()
	{
		JobParameter parameter = new JobParameter(getCurrentTask());
		Map params = new HashMap<String,String>();
		params.put(CURRENT_TASK_NO, parameter);
		JobParameters parameters = new JobParameters(params);
		return parameters;
	}
	
	
	public static long getCurrentTask()
	{
		return atomicLong.getAndIncrement();
	}
}
