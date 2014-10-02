package com.mweb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController
{
	private Log LOGGER = LogFactory.getLog(PageController.class);

	@RequestMapping(value = "/home")
	public String home(Model model)
	{
		return "index";
	}

	//@formatter:off
	@RequestMapping(value = "/login",method={RequestMethod.POST,RequestMethod.GET})
	public String login(@RequestParam(value="username",defaultValue="") String username, 
						@RequestParam(value="password",defaultValue="") String password)
	{
		LOGGER.info("[INFO]"+username+"-"+password);
		return "login";
	}
	//@formatter:on
	
//	@RequestMapping(value="/logout")
//	public String logout()
//	{
//		return "login";
//	}
}
