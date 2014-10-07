/***********************************************************************
 *
 * @file         SAPService.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月7日
 *
 *
 ***********************************************************************/
package com.mweb.repository.plugin;

import org.springframework.stereotype.Repository;

import com.mweb.model.plugin.PK;
import com.mweb.model.plugin.SAPEntity;
import com.mweb.repository.AbstractDataService;

/**
 * @author jet
 *
 */
@Repository
public class SAPService extends AbstractDataService<SAPEntity, PK>
{
	/**
	 * 
	 */
	public SAPService()
	{
		setClazz(SAPEntity.class);
	}
}
