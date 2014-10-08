package com.mweb.batchservice.plugins;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.mweb.model.plugin.SAPEntity;
import com.mweb.model.plugin.SAPEntityModified;

@Component
public class SAPLoadProcessor implements ItemProcessor<SAPEntity, SAPEntityModified>
{

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

}
