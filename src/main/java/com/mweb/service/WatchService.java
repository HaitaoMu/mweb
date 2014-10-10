package com.mweb.service;

import static com.mweb.common.constats.Constants.CURRENT_TASK_NO;

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
	private static final ConcurrentHashMap<String,ProgressRateResult> taskMap = new ConcurrentHashMap<String,ProgressRateResult>();
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
