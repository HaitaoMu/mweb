/***********************************************************************
 *
 * @file         DBController.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月7日
 *
 *
 ***********************************************************************/
package com.mweb.controller.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mweb.model.PageResult;
import com.mweb.repository.DBConfigService;
import com.mweb.repository.plugin.SAPService;

/**
 * @author jet
 *
 */
@Controller
public class DBController
{
	@Autowired
	DBConfigService dbConfigService;
	
	@RequestMapping("/dbConfigIndex")
	public String dbConfigIndex()
	{
		return "databaseManager";
	}
	
	@RequestMapping(value = "/pageDbConfig")
	@ResponseBody
	public PageResult pageDbConfig(
			@RequestParam(value = "pageNum",defaultValue="1") int pageNum,
			@RequestParam(value = "pageSize",defaultValue="10") int pageSize)
	{
		PageResult page = dbConfigService.findByPage(pageNum, pageSize);
		return page;
	}
}
