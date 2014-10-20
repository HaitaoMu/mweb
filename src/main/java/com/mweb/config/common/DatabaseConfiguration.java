package com.mweb.config.common;

import static com.mweb.common.constats.Constants.DATABASE_PROPERTIES_FILE;
import static com.mweb.common.constats.Constants.SCAN_BATCH_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_ENTITY_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_REPOSITORY_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_SERVICE_PACKAGE_NAME;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@PropertySource(DATABASE_PROPERTIES_FILE)
@ComponentScan({SCAN_SERVICE_PACKAGE_NAME,SCAN_ENTITY_PACKAGE_NAME,SCAN_REPOSITORY_PACKAGE_NAME})
public class DatabaseConfiguration
{
	/**
	 * Common Configuration 
	 */

	@Autowired
	public Environment env;
	
	@Bean
	public BasicDataSource dataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.userName"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}
	
	/**
	 * 
	 * Hibernate Configuartion
	 * SessionFactory 
	 * TransactionManager
	 * 
	 */
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager()
	{
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		txManager.setDataSource(dataSource());
		return txManager;
	}
	
	
	
	@Bean(name="localSessionFactory")
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[]
		{
			SCAN_ENTITY_PACKAGE_NAME
		});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	//@formatter:off
	Properties hibernateProperties()
	{
		return new Properties()
		{
			{
				setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

				setProperty("hibernate.c3p0.minPoolSize", env.getProperty("hibernate.c3p0.minPoolSize"));
				setProperty("hibernate.c3p0.maxPoolSize", env.getProperty("hibernate.c3p0.maxPoolSize"));
				setProperty("hibernate.c3p0.timeout", env.getProperty("hibernate.c3p0.timeout"));
				setProperty("hibernate.c3p0.max_statement", env.getProperty("hibernate.c3p0.max_statement"));
				setProperty("hibernate.c3p0.testConnectionOnCheckout",env.getProperty("hibernate.c3p0.testConnectionOnCheckout"));
			}
		};
	}
	//@formatter:on
}
