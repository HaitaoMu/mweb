package com.mweb.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
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

public class AppWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
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
		{ WebMvcConfiguration.class, };
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[]
		{ "/" };
	}

}
