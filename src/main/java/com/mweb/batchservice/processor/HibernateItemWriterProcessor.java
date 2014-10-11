/***********************************************************************
 *
 * @file         HibernateItemWriterProcessor.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月8日
 *
 *
 ***********************************************************************/
package com.mweb.batchservice.processor;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.database.HibernateItemWriter;
import org.springframework.stereotype.Component;

/**
 * @author jet
 *
 */
//@Component
public class HibernateItemWriterProcessor<T extends Serializable> extends HibernateItemWriter<T>
{
	private static Log log = LogFactory.getLog(HibernateItemWriterProcessor.class);


//	@AfterRead
//	public void afterRead()
//	{
//		log.info("[After read]");
//	}
	
	
//	@AfterWrite
//	public void afterWriter()
//	{
//		log.info("[After Writer]");
//	}
}
