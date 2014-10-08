package com.mweb.batchservice.plugins;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.mweb.model.plugin.SAPEntity;
import com.mweb.model.plugin.SAPEntityModified;

@Component
public class SAPLoadProcessor implements ItemProcessor<SAPEntity, SAPEntityModified>
{
	private static Log log = LogFactory.getLog(SAPLoadProcessor.class);

    private static AtomicLong staticsCount = new AtomicLong(0);
    
	
	@Override
	public SAPEntityModified process(SAPEntity item) throws Exception 
	{
		SAPEntityModified entity = new SAPEntityModified();
		entity.setAmount(item.getAmount());
		entity.setPrice(item.getPrice());
		entity.setVolume(item.getVolume());
		entity.setPk(item.getPk());
		return null;
	}

	
	/**
	 * @return the staticsCount
	 */
	public static Long getStaticsCount()
	{
		return staticsCount.longValue();
	}
}
