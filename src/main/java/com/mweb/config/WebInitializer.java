package com.mweb.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//public class MyWebAppInitializer extends AbstractDispatcherServletInitializer
//{
//	@Override
//	protected WebApplicationContext createRootApplicationContext() 
//	{
//		return null;
//	}
//	@Override
//	protected WebApplicationContext createServletApplicationContext()
//	{
//		XmlWebApplicationContext cxt = new XmlWebApplicationContext();
//		cxt.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
//		return cxt;
//	}
//	@Override
//	protected String[] getServletMappings()
//	{
//		return new String[] { "/" };
//	}
//}

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[]
		{
			WebConfiguration.class
		};
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[]
		{
			"/"
		};
	}

}
