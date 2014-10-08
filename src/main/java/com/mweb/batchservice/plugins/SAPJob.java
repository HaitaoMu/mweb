package com.mweb.batchservice.plugins;

import org.springframework.stereotype.Component;

import com.mweb.batchservice.job.TransferDataJob;
import com.mweb.model.plugin.SAPEntity;
import com.mweb.model.plugin.SAPEntityModified;

@Component
public class SAPJob extends TransferDataJob<SAPEntity, SAPEntityModified>
{

	public SAPJob() 
	{
		setClazz(SAPEntity.class);
		setClacc(SAPEntityModified.class);
		setExpressionProcessor(new SAPLoadProcessor());
		setConvertProcessor(new SAPReleaseProcessor());
	}
}
