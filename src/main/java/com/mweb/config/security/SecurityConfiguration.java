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
import com.mweb.repository.CustomUserDetailService;

import static com.mweb.common.constats.Constants.*;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(PACKAGE_NAME)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
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
			.antMatchers("/home").hasAnyAuthority("USER")
			.anyRequest().authenticated();
		http.formLogin()
			.loginPage("/login")
			.permitAll()
			.usernameParameter(USERNAME)
			.passwordParameter(PASSWORD)
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home")
			.and()
			.logout().permitAll();
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
//		@formatter:on

	}
	
	
//	@Bean
//	public CustomUserDetailService customUserDetailsService()
//	{
//		return new CustomUserDetailService();
//	}
}
