package com.mweb.repository;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mweb.AbstractServiceTest;
import com.mweb.common.constats.LockStatus;
import com.mweb.common.constats.PluginType;
import com.mweb.model.BatchLock;
import com.mweb.service.LockService;

public class BatchLockServiceTest extends AbstractServiceTest
{
	@Autowired
	BatchLockService lockService;
	
	BatchLock lock = null;
	
	@Before
	public void init()
	{
		lock = new BatchLock();
		lock.setLockDescription("SAP LOCKED");
		lock.setLockName("SAPLOCK");
		lock.setLockType(PluginType.SAP);
	}
	
	@Test
	public void testSave()
	{
		lock.setLockStatus(LockStatus.LOCKED);
		lockService.save(lock);
	}
	
	@Test
	public void testIsJobRunning()
	{
		lock = lockService.findByType("SAP");
		Assert.assertNotNull(lock);
	}
	
	@Test
	public void testUpdateLock()
	{
		Map<String, String> param = new HashMap<String,String>();
		param.put("lockName", "SAPLOCK");
		Assert.assertEquals(1, lockService.findByParameter(param).size());
		lock = lockService.findByParameter(param).get(0);
		
		lock.setLockStatus(LockStatus.UNLOCK);
		lockService.update(lock);

		
		Assert.assertEquals(LockStatus.UNLOCK, lock.getLockStatus());
	}
	
	@Test
	public void testGetLock()
	{
		Map<String, String> param = new HashMap<String,String>();
		param.put("lockName", "SAPLOCK");
		Assert.assertNotNull(lockService.findByParameter(param));
		Assert.assertEquals(1, lockService.findByParameter(param).size());
		lock = lockService.findByParameter(param).get(0);
		Assert.assertEquals(LockStatus.UNLOCK, lock.getLockStatus());
	}
	
	@Test
	public void testDeleteLock()
	{
		Map<String, String> param = new HashMap<String,String>();
		param.put("lockName", "SAPLOCK");
		Assert.assertNotNull(lockService.findByParameter(param));
		lock = lockService.findByParameter(param).get(0);
		lockService.delete(lock);
		Assert.assertEquals(0,lockService.findByParameter(param).size());
	
	}
}
