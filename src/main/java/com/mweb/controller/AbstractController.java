package com.mweb.controller;

import java.util.Locale;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Component
public abstract class AbstractController
{
	
	@Autowired
	private ApplicationContext context ;
	
	private Locale locale = Locale.US;
	
	protected static final String STATUS = "status";
	
	protected static final String MESSAGE = "message";
	
	public String getMessage(String code)
	{
		return context.getMessage(code, null, locale);
	}
	
	public String getMessageWithParams(String code,String... param)
	{
		return context.getMessage(code, new Object[]{param}, locale);
	}

	public Locale getLocale()
	{
		return locale;
	}

	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}
	
}
