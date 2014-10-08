/***********************************************************************
 *
 * @file         SapController.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月7日
 *
 *
 ***********************************************************************/
package com.mweb.controller.interfaces;

import static com.mweb.common.constats.Constants.SUCCESS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mweb.model.PageResult;
import com.mweb.model.UserInfo;
import com.mweb.model.plugin.SAPEntity;
import com.mweb.repository.plugin.SAPService;

/**
 * @author jet
 *
 */

@Controller
public class SapController
{
	@Autowired
	SAPService sapService;
	
	@RequestMapping("/sapIndex")
	public String Sap()
	{
		return "sapInterface";
	}
	
	@RequestMapping(value = "/pageSapEntity")
	@ResponseBody
	public PageResult pageSapEntity(
			@RequestParam(value = "pageNum",defaultValue="1") int pageNum,
			@RequestParam(value = "pageSize",defaultValue="10") int pageSize)
	{
		PageResult page = sapService.findByPage(pageNum, pageSize);
		return page;
	}
	
	@RequestMapping(value="/sapImportData")
	@ResponseBody
	public String importDataImmediate()
	{
		return SUCCESS;
	}
}
