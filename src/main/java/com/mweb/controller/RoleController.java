/***********************************************************************
 *
 * @file         RoleController.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月16日
 *
 *
 ***********************************************************************/
package com.mweb.controller;

import static com.mweb.common.constats.Constants.SUCCESS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mweb.common.util.EncoderUtil;
import com.mweb.model.PageResult;
import com.mweb.model.UserInfo;
import com.mweb.model.UserRole;
import com.mweb.repository.UserRoleService;

/**
 * @author jet
 *
 */
@Controller
public class RoleController
{
	@Autowired
	private UserRoleService userRoleService;

	private List<String> tableHead = null;

	/**
	 * 
	 */
//	@formatter:off
	public RoleController()
	{
		tableHead = new ArrayList<String>();
		// tableHead.add("USER ID");
		tableHead.add("ROLE NAME");
		tableHead.add("ROLE DESCRIPTION");
		tableHead.add("OPERATION");

	}

	@RequestMapping(value = "/listRole")
	public ModelAndView listUser()
	{
		ModelAndView mv = new ModelAndView();
		List<UserRole> roleList = userRoleService.findAll();
		mv.addObject("roles", roleList);
		mv.addObject("tableHead", tableHead);
		mv.setViewName("rolesManager");
		return mv;
	}
	
	@RequestMapping(value = "/pageRole")
	@ResponseBody
	public PageResult pageUser(
			@RequestParam(value = "pageNum",defaultValue="1") int pageNum,
			@RequestParam(value = "pageSize",defaultValue="10") int pageSize)
	{
		PageResult page = userRoleService.findByPage(pageNum, pageSize);
		return page;
	}

	@RequestMapping(value = "/saveRole")
	@ResponseBody
	public String addUser(
			@RequestParam(value = "roleName") String roleName,
			@RequestParam(value = "roleDescription") String description)
	{
		UserRole role = new UserRole();
		role.setRoleDescription(description);
		role.setRoleName(roleName);
		userRoleService.save(role);
		return SUCCESS;
	}

	@RequestMapping(value = "/updateRole/{roleId}", method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(
			@PathVariable(value = "roleId") String roleId,
			@RequestParam(value = "roleName") String roleName, 
			@RequestParam(value = "roleDescription") String roleDescription)
	{
		UserRole role = userRoleService.findOne(roleId);
		if (null != role)
		{
			role.setRoleDescription(roleDescription);
			role.setRoleName(roleName);
			userRoleService.update(role);
		}
		return SUCCESS;
	}

	//@formatter:on
	@RequestMapping(value = "/deleteUser/{roleId}")
	@ResponseBody
	public String deleteUser(@PathVariable(value = "roleId") String roleId)
	{
		userRoleService.deleteById(roleId);
		return SUCCESS;
	}
}
