package com.mweb.config.common;


import static com.mweb.common.constats.Constants.SCAN_CONFIG_SECURITY_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_CONFIG_SOCKET_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_CONFIG_WEB_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_CONTROLLER_PACKAGE_NAME;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
@Import({DatabaseConfiguration.class,BatchServiceConfiguration.class})
@ComponentScan({SCAN_CONFIG_SECURITY_PACKAGE_NAME,SCAN_CONFIG_SOCKET_PACKAGE_NAME,SCAN_CONFIG_WEB_PACKAGE_NAME,SCAN_CONTROLLER_PACKAGE_NAME})
public class ApplicationConfiguration 
{
	
}
