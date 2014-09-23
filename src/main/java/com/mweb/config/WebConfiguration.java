package com.mweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.mweb")
public class WebConfiguration extends WebMvcConfigurerAdapter
{
	private static final int YEAR_OF_SECONDS = 31556926;
	private static final int MAX_FILE_SIZE = 100000000;

	/**
	 * 添加首页
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/").setViewName("login");
	}

	/**
	 * 判断可接收的media type
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
	{
		configurer.favorPathExtension(false).favorParameter(true);
	}

	/**
	 * 添加共享资源目录
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
				.setCachePeriod(YEAR_OF_SECONDS);
	}

	/**
	 * 
	 * 页面解析
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * Htmp Template
	 * @return
	 */
	@Bean
	public ClassLoaderTemplateResolver templateResolver()
	{
		ClassLoaderTemplateResolver result = new ClassLoaderTemplateResolver();
		result.setPrefix("/WEB-INF/view/");
		result.setSuffix(".html");
		result.setTemplateMode("HTML5");
		return result;
	}

	/**
	 * 文件上传
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver commonMutipartResolver()
	{
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(MAX_FILE_SIZE);
		return resolver;
	}

	/**
	 * 
	 * 国际化资源
	 */
	@Bean
	public ResourceBundleMessageSource resourceBundleMessageSource()
	{
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
