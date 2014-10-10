/***********************************************************************
 *
 * 
 *
 * @file        AbstractBatchJob.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月9日
 *
 *
 ***********************************************************************/
package com.mweb.batchservice.job;

import static com.mweb.common.constats.Constants.DATABASE_PROPERTIES_FILE;
import static com.mweb.common.constats.Constants.SCAN_ENTITY_PACKAGE_NAME;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mweb.common.constats.DBType;
import com.mweb.model.DBConfig;
import com.mweb.repository.DBConfigService;

/**
 * @author jet
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
@PropertySource(DATABASE_PROPERTIES_FILE)
public abstract class AbstractBatchJob
{

	@Autowired
	public Environment env;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	DBConfigService dbConfigService;

	@Autowired
	@Qualifier("localSessionFactory")
	SessionFactory localSessionFactory;

	/**
	 * Spring batch configuration about
	 * 
	 * JobRepository, StepBuilderFactory, JobBuilderFactory, Launcher
	 * 
	 * @throws Exception
	 * 
	 */

//	@Bean
//	public JobRepository repository() 
//	{
//		JobRepository jobRepository = new SimpleJobRepository(
//				new MapJobInstanceDao(), new MapJobExecutionDao(),
//				new MapStepExecutionDao(), new MapExecutionContextDao());
//		return jobRepository;
//	}
//
//	@Bean
//	public StepBuilderFactory stepBuilderFactory()
//	{
//		StepBuilderFactory stepBuilderFactory = null;
//		stepBuilderFactory = new StepBuilderFactory(repository(),new ResourcelessTransactionManager());
//		return stepBuilderFactory;
//	}
//
//	/**
//	 * JobFactory use to create Job
//	 * 
//	 * @throws Exception
//	 */
//	@Bean
//	public JobBuilderFactory jobBuilderFactory() 
//	{
//		JobBuilderFactory factory = new JobBuilderFactory(repository());
//		return factory;
//	}
//
//	@Bean
//	public SimpleJobLauncher launcher() 
//	{
//		SimpleJobLauncher launcher = new SimpleJobLauncher();
//		launcher.setJobRepository(repository());
////		launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//		return launcher;
//	}

	public BasicDataSource getDataSourceByName(DBConfig db)
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(db.getDriver());
		dataSource.setUrl(db.getUrl());
		dataSource.setUsername(db.getUsername());
		dataSource.setPassword(db.getPassword());
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager(
			SessionFactory sessionFactory, BasicDataSource dataSource)
	{
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		txManager.setDataSource(dataSource);
		return txManager;
	}

	public SessionFactory getSessionFactory(String name)
	{
		DBConfig dbConfig = dbConfigService.getDBByName(name);
		BasicDataSource datasource = getDataSourceByName(dbConfig);

		//@formatter:off
		Properties properties = hibernateProperties();
		properties.setProperty("hibernate.connection.url", dbConfig.getUrl());
		properties.setProperty("hibernate.connection.driver_class",dbConfig.getDriver());
		properties.setProperty("hibernate.connection.password",dbConfig.getPassword());
		properties.setProperty("hibernate.connection.username",dbConfig.getUsername());

		if (dbConfig.getDbType().equals(DBType.MYSQL)) 
		{
			properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		} 
		else if (dbConfig.getDbType().equals(DBType.ORACLE)) 
		{
			properties.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle9iDialect");
		}
		//formatter:off
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(datasource);

		sessionBuilder.scanPackages(SCAN_ENTITY_PACKAGE_NAME);
		sessionBuilder.setProperties(properties);

		return sessionBuilder.buildSessionFactory();

	}

	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto",
						env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.show_sql",
						env.getProperty("hibernate.show_sql"));
				setProperty(
						"hibernate.current_session_context_class",
						env.getProperty("hibernate.current_session_context_class"));
				setProperty("hibernate.c3p0.minPoolSize",
						env.getProperty("hibernate.c3p0.minPoolSize"));
				setProperty("hibernate.c3p0.maxPoolSize",
						env.getProperty("hibernate.c3p0.maxPoolSize"));
				setProperty("hibernate.c3p0.timeout",
						env.getProperty("hibernate.c3p0.timeout"));
				setProperty("hibernate.c3p0.max_statement",
						env.getProperty("hibernate.c3p0.max_statement"));
				setProperty(
						"hibernate.c3p0.testConnectionOnCheckout",
						env.getProperty("hibernate.c3p0.testConnectionOnCheckout"));
			}
		};
	}

}
