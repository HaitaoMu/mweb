package com.mweb.config.security;

import static com.mweb.common.constats.Constants.PACKAGE_NAME;
import static com.mweb.common.constats.Constants.ROLE_PREFIX;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import com.mweb.common.util.EncoderUtil;
import com.mweb.repository.security.dao.AuthenticateHandler;
import com.mweb.repository.security.dao.CustomUserDetailService;
import com.mweb.repository.security.dao.InvocationSecurityMetadataSourceService;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(PACKAGE_NAME)
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

//	@formatter:off
	
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	@Autowired
	private AuthenticateHandler authenticateHandler;
	
	@Autowired
	public CustomUserDetailService customUserDetailsService;
	
//	@Autowired
//	public CustomeAccessDecisionManager customAccessDecisionManager;
	
	@Autowired
	public InvocationSecurityMetadataSourceService securityMetaDatasource;
	
//	@Bean
//	public List<AccessDecisionVoter> getDecisionVoters()
//	{
//	        List<AccessDecisionVoter> decisionVoters = new ArrayList<AccessDecisionVoter>();
//	        WebExpressionVoter expressionVoter = new WebExpressionVoter();
//	        decisionVoters.add(expressionVoter);
//	        return decisionVoters;
//	}
	
	@Bean
	public AccessDecisionManager defaultAccessDecisionManager() {
	    List<AccessDecisionVoter> voters = new ArrayList<AccessDecisionVoter>();
	    voters.add(new WebExpressionVoter());
	    
	    RoleVoter roleVoter = new RoleVoter();
	    roleVoter.setRolePrefix(ROLE_PREFIX);
	    voters.add(roleVoter);

	    AccessDecisionManager result = new AffirmativeBased(voters);
	    return result;
	}
	@Bean
	public FilterSecurityInterceptor getFilterSecurityInterceptor() throws Exception
	{
		FilterSecurityInterceptor filterSecurityInteceptor = new FilterSecurityInterceptor();
		filterSecurityInteceptor.setAuthenticationManager(authenticationManagerBean());
		filterSecurityInteceptor.setAccessDecisionManager(defaultAccessDecisionManager());
		filterSecurityInteceptor.setSecurityMetadataSource(securityMetaDatasource);
		return filterSecurityInteceptor;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	{
		try
		{
//			auth.authenticationProvider(new PreAuthenticatedAuthenticationProvider())
//				.userDetailsService(customUserDetailsService)
//				.passwordEncoder(EncoderUtil.passwordEncoder);
			auth.userDetailsService(customUserDetailsService).passwordEncoder(EncoderUtil.passwordEncoder);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void configure(HttpSecurity http) throws Exception
	{

		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/index").permitAll()
			.anyRequest()
			.authenticated();
		http.formLogin()
			.loginPage("/index")
			.failureHandler(authenticateHandler)
			.permitAll()
			.usernameParameter(USERNAME)
			.passwordParameter(PASSWORD)
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home")
			.and()
			.logout()
			.logoutUrl("/logout")
			.deleteCookies("remove")
		    .invalidateHttpSession(false)
			.permitAll()
			.and()
		    .sessionManagement().maximumSessions(1).expiredUrl("/index");
		http.headers()
			.contentTypeOptions()
	    	.xssProtection()
	    	.cacheControl()
	    	.httpStrictTransportSecurity()
	    	.frameOptions();
		http.addFilterAfter(getFilterSecurityInterceptor(),FilterSecurityInterceptor.class);

	}

	@Configuration
	@Order(2)
	protected static class AuthenticationConfiguration extends
			GlobalAuthenticationConfigurerAdapter
	{

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception
		{
//			auth.ldapAuthentication().userDnPatterns("uid={0},ou=people")
//					.groupSearchBase("ou=groups").contextSource()
//					.ldif("classpath:mweb.ldif");
		}
	}
//	 @formatter:on
}
