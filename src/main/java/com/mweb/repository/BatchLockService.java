package com.mweb.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.ProjectionList;
import org.junit.Assert;
import org.springframework.stereotype.Repository;

import com.mweb.common.constats.PluginType;
import com.mweb.model.BatchLock;

@Repository
public class BatchLockService extends AbstractDataService<BatchLock, String>
{
	private static final int ZERO = 0;

	public BatchLockService()
	{
		setClazz(BatchLock.class);
	}
	
	public BatchLock findByType(String type)
	{
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("lockType",PluginType.parseType(type));
		List<BatchLock> locks = findByParameter(param);
		if(null!=locks && locks.size() > ZERO)
		{
			return locks.get(ZERO);
		}
		return null;
	}

}
