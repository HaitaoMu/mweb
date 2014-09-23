package com.mweb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	{
		try
		{
//			auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser("user").password("password")
//					.roles("USER").and().withUser("admin").password("password").roles("USER", "ADMIN");
			auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

//	@Configuration
//	@Order(1)
//	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter
//	{
//		protected void configure(HttpSecurity http) throws Exception
//		{
//			http.antMatcher("/api/**").authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic();
//		}
//	}
//
//	@Configuration
//	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter
//	{
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception
//		{
//			// CSRF protection
//			http.csrf().disable();
//			http.authorizeRequests().anyRequest().authenticated().and().formLogin();
//			http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//		}
//	}
}
