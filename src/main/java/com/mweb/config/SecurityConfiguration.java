package com.mweb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
//	@Autowired
//	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	{
		try
		{
			// auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser("user").password("password")
			// .roles("USER").and().withUser("admin").password("password").roles("USER", "ADMIN");
			// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void configure(HttpSecurity http) throws Exception
	{
		// http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

		// http.antMatcher("/api/**").authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic();
		http.authorizeRequests().antMatchers("/resources/**").hasRole("USER").antMatchers("/home").hasRole("USER")
				.anyRequest().authenticated();
		http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();

	}
}
