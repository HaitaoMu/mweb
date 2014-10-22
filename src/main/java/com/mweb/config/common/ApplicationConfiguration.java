package com.mweb.config.common;

import static com.mweb.common.constats.Constants.SCAN_CONFIG_SECURITY_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_CONFIG_SOCKET_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_CONFIG_WEB_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_CONTROLLER_PACKAGE_NAME;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
@EnableScheduling
//@EnableAspectJAutoProxy
@EnableCaching

@Import(
{ DatabaseConfiguration.class, BatchServiceConfiguration.class })
@ComponentScan(
{ SCAN_CONFIG_SECURITY_PACKAGE_NAME, SCAN_CONFIG_SOCKET_PACKAGE_NAME,
		SCAN_CONFIG_WEB_PACKAGE_NAME, SCAN_CONTROLLER_PACKAGE_NAME })
public class ApplicationConfiguration
{
	@Bean
	public CacheManager cacheManager()
	{
		return new ConcurrentMapCacheManager();
	}

}
