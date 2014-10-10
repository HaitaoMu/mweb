/***********************************************************************
 *
 * 
 *
 * @file        EntityItemWriter.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月14日
 *
 *
 ***********************************************************************/
package com.mweb.batchservice.processor;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * @author jet
 *
 */
public class EntityItemWriter<T> implements ItemWriter<T>
{

	private static Log log = LogFactory.getLog(EntityItemWriter.class);

	SessionFactory sessionFactory = null;
	
	/**
	 * 
	 */
	public EntityItemWriter(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
	}
	
	
	public void write(List<? extends T> items) throws Exception
	{
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		for (T item : items)
		{
			session.save(item);
		}
		transaction.commit();
	}

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

//	@BeforeWrite
//	public void beforeWriter()
//	{
//		log.debug("[Before Writer]");
//	}
//	
//	@AfterWrite
//	public void afterWriter()
//	{
//		log.debug("[After Writer]");
//	}
	
}
