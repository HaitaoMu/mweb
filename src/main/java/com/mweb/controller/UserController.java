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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mweb.common.util.EncoderUtil;
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
	
	@RequestMapping(value="/listUser")
	public ModelAndView listUser()
	{
		ModelAndView mv = new ModelAndView();
		List<UserInfo> userList = userInfoService.findAll();
		mv.addObject("list",userList);
		mv.setViewName("usersManager");
		return mv;
	}
	
	@RequestMapping(value="/addUser")
	public void addUser(@RequestParam(value="username") String username,
						@RequestParam(value="password") String password,
						@RequestParam(value="description") String description)
	{
		UserInfo user = new UserInfo();
		user.setUserDescription(description);
		user.setUserName(username);
		user.setUserPassword(EncoderUtil.passwordEncoder.encodePassword(password, null));
		userInfoService.save(user);
	}
}
