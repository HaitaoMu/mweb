package com.mweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.mweb.model.ProgressRateResult;

@Service
public class WatchService 
{
	private static final ConcurrentHashMap<String,ProgressRateResult> taskMap = new ConcurrentHashMap<String,ProgressRateResult>();
	
	public static void putTask(ProgressRateResult result)
	{
		taskMap.put(result.getTaskId(),result);
	}
	
	public static ProgressRateResult getProgressResult(String taskId)
	{
		return taskMap.get(taskId);
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
}
