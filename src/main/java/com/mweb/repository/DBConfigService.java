/***********************************************************************
 *
 * 
 *
 * @file        CFConfigService.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月13日
 *
 *
 ***********************************************************************/
package com.mweb.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mweb.model.DBConfig;

/**
 * @author jet
 *
 */
@Repository
public class DBConfigService extends AbstractDataService<DBConfig, Long>
{

	private static final int ZERO = 0;

	public DBConfigService()
	{
		setClazz(DBConfig.class);
	}

	public DBConfig getDBByName(String name)
	{
		DBConfig db = null;
		Map parameters = new HashMap<String,String>();
		parameters.put("dbName", name);
		
		
		List<DBConfig> dbs = findByParameter(parameters);
		if(null != dbs && dbs.size() > ZERO)
		{
			db = dbs.get(ZERO);
		}
		return db;
	}

}
