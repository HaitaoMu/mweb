package com.mweb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.mweb.config.common.ApplicationConfiguration;

@Configuration
@Import(ApplicationConfiguration.class)
@ComponentScan({"com.mweb.model","com.mweb.repository"})
public class AppConfiguration
{

}
