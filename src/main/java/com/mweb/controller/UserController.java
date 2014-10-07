/***********************************************************************
 *
 * @file         UserController.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月4日
 *
 *
 ***********************************************************************/
package com.mweb.controller;

import static com.mweb.common.constats.Constants.SUCCESS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mweb.common.util.EncoderUtil;
import com.mweb.model.PageResult;
import com.mweb.model.UserInfo;
import com.mweb.repository.UserInfoService;
/**
 * @author jet
 *
 */

@Controller
public class UserController
{
	@Autowired
	private UserInfoService userInfoService;

	private List<String> tableHead = null;

	/**
	 * 
	 */
//	@formatter:off
	public UserController()
	{
		tableHead = new ArrayList<String>();
		// tableHead.add("USER ID");
		tableHead.add("USER NAME");
		tableHead.add("USER PASSWORD");
		tableHead.add("USER DESCRIPTION");
		tableHead.add("OPERATION");

	}

	@RequestMapping(value = "/listUser")
	public ModelAndView listUser()
	{
		ModelAndView mv = new ModelAndView();
		List<UserInfo> userList = userInfoService.findAll();
		mv.addObject("users", userList);
		mv.addObject("tableHead", tableHead);
		mv.setViewName("usersManager");
		return mv;
	}
	
	@RequestMapping(value = "/pageUser")
	@ResponseBody
	public PageResult pageUser(
			@RequestParam(value = "pageNum",defaultValue="1") int pageNum,
			@RequestParam(value = "pageSize",defaultValue="10") int pageSize)
	{
		PageResult page = userInfoService.findByPage(pageNum, pageSize);
		return page;
	}

	@RequestMapping(value = "/saveUser")
	@ResponseBody
	public String addUser(
			@RequestParam(value = "userName") String username,
			@RequestParam(value = "userPassword") String password,
			@RequestParam(value = "userDescription") String description)
	{
		UserInfo user = new UserInfo();
		user.setUserDescription(description);
		user.setUserName(username);
		user.setUserPassword(EncoderUtil.passwordEncoder.encodePassword(password, null));
		userInfoService.save(user);
		return SUCCESS;
	}

	@RequestMapping(value = "/updateUser/{userId}", method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "userName") String username, 
			@RequestParam(value = "userPassword") String password,
			@RequestParam(value = "userDescription") String description)
	{
		UserInfo user = userInfoService.findOne(userId);
		if (null != user)
		{
			user.setUserDescription(description);
			user.setUserName(username);
			user.setUserPassword(EncoderUtil.passwordEncoder.encodePassword(password, null));
			userInfoService.update(user);
		}
		return SUCCESS;
	}

	//@formatter:on
	@RequestMapping(value = "/deleteUser/{userId}")
	@ResponseBody
	public String deleteUser(@PathVariable(value = "userId") String userId)
	{
		userInfoService.deleteById(userId);
		return SUCCESS;
	}
}
