package com.mweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mweb.common.constats.LockStatus;
import com.mweb.common.constats.PluginType;
import com.mweb.common.util.PluginTypeUtil;
import com.mweb.model.BatchLock;
import com.mweb.repository.BatchLockService;

@Service
public class LockService
{
	@Autowired
	BatchLockService lockService;
	
	
	public synchronized  boolean checkRunningJob(String type)
	{
		boolean isRunning = false;
		BatchLock lock = null;
		try
		{
			lock = lockService.findByType(type);
			if(null == lock || LockStatus.UNLOCK.equals(lock.getLockStatus()))
			{
				isRunning = true;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isRunning;
	}
	
	public synchronized void lockRunningJob(String type)
	{
		BatchLock lock = null;
		try
		{
			lock = lockService.findByType(type.toString());
			if(null == lock)
			{
				lock = new BatchLock();
			}
			lock.setLockDescription(type +" BATCH JOB LOCK");
			lock.setLockName(type+"LOCK");
			lock.setLockStatus(LockStatus.LOCKED);
			lock.setLockType(PluginType.parseType(type));
			lockService.update(lock);

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public synchronized void unlockRunningJob(String type)
	{
		BatchLock lock = null;
		try
		{
			lock = lockService.findByType(type);
			if(null != lock)
			{
				lock.setLockStatus(LockStatus.UNLOCK);
				lockService.update(lock);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
