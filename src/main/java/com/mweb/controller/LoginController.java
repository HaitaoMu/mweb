package com.mweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController
{

	  @RequestMapping(value="/home")
	  public String hello(Model model)
	  {
		  return "index";
	  }
}
