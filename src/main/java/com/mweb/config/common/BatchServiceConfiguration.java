package com.mweb.config.common;

import static com.mweb.common.constats.Constants.SCAN_BATCH_PACKAGE_NAME;
import org.springframework.batch.core.JobParametersBuilder;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({SCAN_BATCH_PACKAGE_NAME})
public class BatchServiceConfiguration
{
	/**
	 * Spring batch configuration about 
	 * 
	 * JobRepository,
	 * StepBuilderFactory,
	 * JobBuilderFactory,
	 * Launcher
	 * 
	 */
	
	@Bean
	public JobRepository repository()
	{
		JobRepository repository = new SimpleJobRepository(
				new MapJobInstanceDao(), new MapJobExecutionDao(),
				new MapStepExecutionDao(), new MapExecutionContextDao());
		return repository;
	}

	/**
	 *  JobFactory use to create Job
	 */
	@Bean
	public JobBuilderFactory jobBuilderFactory()
	{
		JobBuilderFactory factory = new JobBuilderFactory(repository());
		return factory;
	}

	@Bean
	public SimpleJobLauncher launcher()
	{
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(repository());
		return launcher;
	}
	
	@Bean
	public StepBuilderFactory stepBuilderFactory()
	{
		StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(
				repository(), new ResourcelessTransactionManager());
		return stepBuilderFactory;
	}
	
	
	@Bean
	public JobParametersBuilder parameterBuilder()
	{
		return  new JobParametersBuilder();
	}
	
}
