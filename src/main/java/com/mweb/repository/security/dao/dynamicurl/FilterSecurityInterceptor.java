package com.mweb.repository.security.dao.dynamicurl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

@Component
public class FilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter
{

	private static final Log logger = LogFactory.getLog(FilterSecurityInterceptor.class);

	@Autowired
    FilterInvocationSecurityMetadataSource securityMetadataSource;
	
//	@Autowired
//	CustomeAccessDecisionManager accessDecisionManager;
	
	@Bean
	public AccessDecisionManager defaultAccessDecisionManager() {
	    List<AccessDecisionVoter> voters = new ArrayList<AccessDecisionVoter>();
	    voters.add(new WebExpressionVoter());
	   
	    AccessDecisionManager result = new AffirmativeBased(voters);
	    return result;
	}
	
	@Bean
	public RoleVoter roleVoter() {
	    RoleVoter roleVoter = new RoleVoter();
	    roleVoter.setRolePrefix("");
	    return roleVoter;
	}

	@Bean
	public AffirmativeBased accessDecisionManager() {
	    AffirmativeBased affirmativeBased = new AffirmativeBased(Arrays.asList((AccessDecisionVoter)roleVoter()));
	    affirmativeBased.setAllowIfAllAbstainDecisions(false);
	    return affirmativeBased;
	}
	
	@Override
	public void destroy()
	{
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - start"); //$NON-NLS-1$  
		}
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
		if (logger.isDebugEnabled())
		{
			logger.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end"); //$NON-NLS-1$  
		}

	}

	public void invoke(FilterInvocation fi) throws IOException,
			ServletException
	{
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try
		{
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally
		{
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
	}

	@Override
	public Class<?> getSecureObjectClass()
	{
		 return FilterInvocation.class;  
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource()
	{
		return this.securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource)
	{
		this.securityMetadataSource = securityMetadataSource;
	}
	
	/* (non-Javadoc)
	 */
	@Bean
	public List<AccessDecisionVoter> accessDecisionVoter()
	{
		List<AccessDecisionVoter> voters = new ArrayList<AccessDecisionVoter>();
		voters.add(new RoleVoter());
		return voters;
	}
}
