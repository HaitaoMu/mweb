package com.mweb.config.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import com.mweb.common.util.EncoderUtil;
import com.mweb.repository.security.dao.AuthenticateHandler;
import com.mweb.repository.security.dao.CustomUserDetailService;
import com.mweb.repository.security.dao.CustomeAccessDecisionManager;

import static com.mweb.common.constats.Constants.*;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(PACKAGE_NAME)
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	@Autowired
	private AuthenticateHandler authenticateHandler;
	
//	@Autowired
//	private CustomeAccessDecisionManager accessDecisionManager;
	// @Resource(name="customUserDetailsService")

//	private JaasAuthenticationProvider authenticationProvider;

	@Autowired
	public CustomUserDetailService customUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	{
		try
		{
//			@formatter:off
//			auth.inMemoryAuthentication()
//				.withUser("username").password("password")
//				.roles("USER");
//			auth.authenticationProvider(authenticationProvider);
			auth.userDetailsService(customUserDetailsService).passwordEncoder(EncoderUtil.passwordEncoder);
//			auth.
//			@formatter:on
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void configure(HttpSecurity http) throws Exception
	{
//		@formatter:off
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/login").permitAll()
//			.accessDecisionManager(accessDecisionManager)
			.antMatchers("/404").hasRole("USER")
			.antMatchers("/home").hasRole("USER")
			.anyRequest()
			.authenticated();
		http.formLogin()
			.loginPage("/login")
			.failureHandler(authenticateHandler)
			.permitAll()
			.usernameParameter(USERNAME)
			.passwordParameter(PASSWORD)
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home")
			.and()
			.logout()
			.logoutUrl("/logout")
			.permitAll()
			.and()
		    .sessionManagement().maximumSessions(1);
		
			List<Filter> filters = getFilterList();
			for(Filter filter : filters)
			{
				http.addFilter(filter);
			}
	//		@formatter:on

	}

	private List<Filter> getFilterList()
	{
		List<Filter> filters = new ArrayList<Filter>();
		return filters;
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

	// @Bean
	// public CustomUserDetailService customUserDetailsService()
	// {
	// return new CustomUserDetailService();
	// }
}
