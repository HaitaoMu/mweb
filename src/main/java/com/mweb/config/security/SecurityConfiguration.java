package com.mweb.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mweb.common.util.EncoderUtil;
import com.mweb.repository.security.dao.AuthenticateHandler;
import com.mweb.repository.security.dao.CustomUserDetailService;

import static com.mweb.common.constats.Constants.*;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(PACKAGE_NAME)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	@Autowired
	private AuthenticateHandler authenticateHandler;
//	@Resource(name="customUserDetailsService")
	
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
//			@formatter:on
		}
		catch (Exception e)
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
//		@formatter:on

	}
	
	
//	@Bean
//	public CustomUserDetailService customUserDetailsService()
//	{
//		return new CustomUserDetailService();
//	}
}
