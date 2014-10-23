/***********************************************************************
 *
 * @file         MenuController.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月3日
 *
 *
 ***********************************************************************/
package com.mweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mweb.model.PageResult;
import com.mweb.repository.DBConfigService;
import com.mweb.repository.TreeMenuService;

/**
 * @author jet
 *
 */

@Controller
public class MenuController
{

	@Autowired
	TreeMenuService treeMenuService;
	
	@RequestMapping("/menu")
	public String dbConfigIndex()
	{
		return "menuManager";
	}
	
	@RequestMapping(value = "/pageMenuConfig")
	@ResponseBody
	public PageResult pageDbConfig(
			@RequestParam(value = "pageNum",defaultValue="1") int pageNum,
			@RequestParam(value = "pageSize",defaultValue="10") int pageSize)
	{
		PageResult page = treeMenuService.findByPage(pageNum, pageSize);
		return page;
	}
	
	@RequestMapping(value = "/saveMenu")
	@ResponseBody
	public PageResult saveMenu(
			@RequestParam(value = "pageNum",) int pageNum,
			@RequestParam(value = "pageSize",defaultValue="10") int pageSize)
	{
		PageResult page = treeMenuService.findByPage(pageNum, pageSize);
		return page;
	}
}
