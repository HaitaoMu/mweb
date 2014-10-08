package com.mweb.batchservice.plugins;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.mweb.model.plugin.SAPEntity;
import com.mweb.model.plugin.SAPEntityModified;

@Component
public class SAPReleaseProcessor implements
		ItemProcessor<SAPEntityModified, SAPEntity> {

	@Override
	public SAPEntity process(SAPEntityModified item) throws Exception {
		SAPEntity entity = new SAPEntity();
		entity.setAmount(item.getAmount()+80);
		entity.setPrice(item.getPrice()+100);
		entity.setVolume(item.getVolume()+200);
		entity.setPk(item.getPk());
		return entity;
	}

}
