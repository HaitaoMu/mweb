package com.mweb.repository.security.dao.dynamicurl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomeAuthenticationManager extends AbstractAccessDecisionManager
{

	private static final Log logger = LogFactory
			.getLog(CustomeAuthenticationManager.class);
	
	 private List<AccessDecisionVoter> decisionVoters;

	 
	 /**
	 * 
	 */
	public CustomeAuthenticationManager()
	{
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public CustomeAuthenticationManager(List<AccessDecisionVoter> voters)
	{
		// TODO Auto-generated constructor stub
		this.decisionVoters = voters;
	}
	
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - start"); //$NON-NLS-1$  
		}
		if (configAttributes == null)
		{
			if (logger.isDebugEnabled())
			{
				logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - end"); //$NON-NLS-1$  
			}
			return;
		}
		if (logger.isDebugEnabled())
		{
			logger.debug("正在访问的url是：" + object.toString());
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext())
		{
			ConfigAttribute ca = ite.next();
			logger.debug("needRole is：" + ca.getAttribute());
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities())
			{
				logger.debug("/t授权信息是：" + ga.getAuthority());
				if (needRole.equals(ga.getAuthority()))
				{ 
					// ga is user's role.
					if (logger.isDebugEnabled())
					{
						logger.debug("判断到，needRole 是" + needRole + ",用户的角色是:"+ ga.getAuthority() + "，授权数据相匹配");
						logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - end"); //$NON-NLS-1$  
					}
					return;
				}
			}
		}
		throw new AccessDeniedException("没有权限");
	}



}
