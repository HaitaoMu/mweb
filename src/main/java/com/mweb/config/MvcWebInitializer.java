package com.mweb.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


@Order(1)
public class MvcWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class[]
		{ SecurityConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[]
		{ MvcConfiguration.class, };
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[]
		{ "/" };
	}

}
