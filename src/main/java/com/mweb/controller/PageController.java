package com.mweb.controller;

import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView login(
						@RequestParam(value="username",defaultValue="",required=false) String username, 
						@RequestParam(value="password",defaultValue="",required=false) String password,
						@RequestParam(value="error",required=false) String error)
	{
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		return model;
	}
	//@formatter:on
	
	@RequestMapping(value="/logout")
	public String logout()
	{
		return "login";
	}
}
