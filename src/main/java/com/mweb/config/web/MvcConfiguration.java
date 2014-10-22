package com.mweb.config.web;

import static com.mweb.common.constats.Constants.SCAN_CONTROLLER_PACKAGE_NAME;

import java.util.Properties;

import javax.servlet.Filter;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.mweb.controller.interceptor.RequestProcessingTimeInterceptor;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(SCAN_CONTROLLER_PACKAGE_NAME)
public class MvcConfiguration extends WebMvcConfigurerAdapter
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
//		registry.addViewController("/404").setViewName("404");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
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
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(YEAR_OF_SECONDS);
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
	 * 
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
	
//	@Bean
//	public ResourceBundleMessageSource resourceBundleMessageSource()
//	{
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}

	@Bean
	public MessageSource messageSource()
	{

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
	{
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		Properties exceptions = new Properties();
		exceptions.put("NoSuchRequestHandlingMethodException", "404");
		exceptionResolver.setExceptionMappings(exceptions);
		
		Properties statusCodes = new Properties();
		statusCodes.put("404 Not Found", "404");
		exceptionResolver.setStatusCodes(statusCodes);

		return exceptionResolver;
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry)
	{
		registry.addInterceptor(new LocaleChangeInterceptor());
		registry.addInterceptor(new RequestProcessingTimeInterceptor());

	}

	@Bean
	MappingJackson2HttpMessageConverter converter()
	{
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		// do your customizations here...
		return converter;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}

}
