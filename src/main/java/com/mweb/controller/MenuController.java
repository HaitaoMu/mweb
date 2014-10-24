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
import com.mweb.model.Result;
import com.mweb.model.common.TreeMenuEntity;
import com.mweb.repository.DBConfigService;
import com.mweb.repository.TreeMenuService;

import static com.mweb.common.constats.Constants.FAIL;
import static com.mweb.common.constats.Constants.SUCCESS;

/**
 * @author jet
 *
 */

@Controller
public class MenuController extends AbstractController
{

	@Autowired
	TreeMenuService treeMenuService;
	
	@RequestMapping("/menu")
	public ModelAndView dbConfigIndex()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", treeMenuService.findAll());
		mv.setViewName("menuManager");
		return mv;
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
	public Result saveMenu(
			@RequestParam(value="parentId") int Id,
			@RequestParam(value="menuName") String menuName,
			@RequestParam(value="menuUrl") String menuUrl,
			@RequestParam(value="menuIcon") String menuIcon,
			@RequestParam(value="menuDescription") String menuDescription			
			)
	{
		TreeMenuEntity entity = new TreeMenuEntity();
		entity.setMenuDescription(menuDescription);
		entity.setMenuUrl(menuUrl);
		entity.setMenuIcon(menuIcon);
		entity.setName(menuName);
		treeMenuService.addSubMenu(entity, Id);
		return new Result(SUCCESS,getMessage("CREATE_MENU_SUCCESS"));
	}
	
	@RequestMapping(value = "/updateMenu")
	@ResponseBody
	public Result updateMenu(
			@RequestParam(value="menuId") int menuId,
			@RequestParam(value="menuName") String menuName,
			@RequestParam(value="menuUrl") String menuUrl,
			@RequestParam(value="menuIcon") String menuIcon,
			@RequestParam(value="menuDescription") String menuDescription			
			)
	{
		TreeMenuEntity entity = treeMenuService.findOne(menuId);
		if(null!=entity)
		{
			entity.setMenuDescription(menuDescription);
			entity.setMenuUrl(menuUrl);
			entity.setMenuIcon(menuIcon);
			entity.setName(menuName);
			treeMenuService.update(entity);
			return new Result(SUCCESS,getMessage("CREATE_MENU_SUCCESS"));
		}
		return new Result(FAIL,getMessage("CREATE_MENU_FAILED"));
	}
	
	@RequestMapping(value = "/deleteMenu")
	@ResponseBody
	public Result deleteMenu(@RequestParam(value="menuId") int menuId)
	{
		treeMenuService.deleteById(menuId);
		return new Result(SUCCESS,getMessage("CREATE_MENU_SUCCESS"));
	}
}
