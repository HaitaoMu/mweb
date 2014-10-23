package com.mweb.config;

import static com.mweb.common.constats.Constants.SCAN_CONFIG_SECURITY_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_CONFIG_SOCKET_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_CONFIG_WEB_PACKAGE_NAME;
import static com.mweb.common.constats.Constants.SCAN_CONTROLLER_PACKAGE_NAME;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mweb.config.common.ApplicationConfiguration;
import com.mweb.config.common.BatchServiceConfiguration;
import com.mweb.config.common.DatabaseConfiguration;

@Configuration
@EnableTransactionManagement
@WebAppConfiguration
@Import(
{ DatabaseConfiguration.class })
public class AppConfiguration
{

}
