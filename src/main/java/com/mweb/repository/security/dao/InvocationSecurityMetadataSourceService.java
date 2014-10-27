package com.mweb.repository.security.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

public class InvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource
{

	private static final Log logger = LogFactory
			.getLog(InvocationSecurityMetadataSourceService.class);
	private AntPathMatcher urlMatcher = new AntPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("getAttributes(Object) - start"); //$NON-NLS-1$  
		}
		// guess object is a URL.
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getRequestUrl();
		String httpMethod = fi.getRequest().getMethod();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext())
		{
			String resURL = ite.next();
			if (urlMatcher.match(url, resURL))
			{
				Collection<ConfigAttribute> returnCollection = resourceMap.get(resURL);
				if (logger.isDebugEnabled())
				{
					logger.debug("getAttributes(Object) - end"); //$NON-NLS-1$  
				}
				return returnCollection;
			}
		}
		if (logger.isDebugEnabled())
		{
			logger.debug("getAttributes(Object) - end"); //$NON-NLS-1$  
		}
		return null;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes()
	{
		return null;
	}

	public boolean supports(Class<?> clazz)
	{
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
