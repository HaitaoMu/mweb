/***********************************************************************
 *
 * 
 *
 * @file        AbstractDataService.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月8日
 *
 *
 ***********************************************************************/
package com.mweb.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jet
 *
 */
@Component
@Transactional(propagation=Propagation.REQUIRED)
public abstract class AbstractDataService<T extends Serializable, PK extends Serializable>
extends HibernateDaoSupport
{

	private Class<T> clazz;
	
	public void setClazz(final Class<T> clazzToSet)
	{
		clazz = clazzToSet;
	}

	public T findOne(PK pk)
	{
		return (T) getCurrentSession().get(clazz, pk);
	}

	public List<T> findByParameter(Map parameters)
	{

		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.allEq(parameters));
		List<T> results = (List<T>) getHibernateTemplate().findByCriteria(criteria);
		return results;

	}

	public List<T> findAll()
	{
		Criteria criteria = getCurrentSession().createCriteria(clazz);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<T> results = criteria.list();
		return results;
	}

	public void save(final T entity)
	{
		getCurrentSession().save(entity);
	}

	public T update(final T entity)
	{
		return (T) getCurrentSession().merge(entity);
	}

	public void delete(final T entity)
	{
		getCurrentSession().delete(entity);
	}

	public void deleteById(PK pk)
	{
		final T entity = findOne(pk);
		delete(entity);
	}

	protected final Session getCurrentSession()
	{
		return currentSession();
	}
	
	@Resource(name = "localSessionFactory")
	public void setLocalSessionFactory(SessionFactory sessionFactory)
	{
		super.setSessionFactory(sessionFactory);
	}
	
}
