package com.mweb.service;

import java.util.Vector;

import org.springframework.stereotype.Service;

import com.mweb.model.ProgressRateResult;

@Service
public class WatchService 
{
	private static final Vector<ProgressRateResult> taskVector = new Vector<ProgressRateResult>();
	
	public static void putTask(ProgressRateResult result)
	{
		taskVector.add(result);
	}
	
	public static Vector<ProgressRateResult> getTaskvector() 
	{
		return taskVector;
	}
}
