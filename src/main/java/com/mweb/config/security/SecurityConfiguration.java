package com.mweb.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	{
		try
		{
//			@formatter:off
			ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			auth.inMemoryAuthentication()
				.passwordEncoder(passwordEncoder)
				.withUser("user").password("password")
				.roles("USER");
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
		http.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/home").hasRole("USER")
			.anyRequest().authenticated();
		http.formLogin()
			.loginPage("/login").permitAll()
			.usernameParameter(USERNAME)
			.passwordParameter(PASSWORD)
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home")
			.and()
			.logout().permitAll();
//		@formatter:on

	}
}
