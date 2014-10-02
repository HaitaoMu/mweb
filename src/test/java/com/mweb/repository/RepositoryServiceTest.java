package com.mweb.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mweb.config.common.ApplicationConfiguration;
import com.mweb.model.UserInfo;
import com.mweb.model.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class RepositoryServiceTest {
	
	private static final String SALT = null;
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	CustomUserDetailService customUserDetailsService;
	
	ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
	
	
	@Test
	public void testSaveUser(){
	
		UserInfo user = new UserInfo();
		user.setUserName("Jet");
		user.setUserPassword(passwordEncoder.encodePassword("123456", SALT));
		user.setUserDescription("User for test");
	
	
		UserRole role = new UserRole();
		role.setRoleDescription("Role for test");
		role.setRoleName("Administrator");
		
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(role);
		
		user.setRoles(roles);
		userInfoService.save(user);
	}
	
	@Test
	public void testGetUset(){
		
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("userName", "Jet");
		
		List<UserInfo> users = userInfoService.findByParameter(parameters);
		System.out.println(users.get(0));
		
		Set<UserRole> roles = users.get(0).getRoles();
		Iterator<UserRole> iterator = roles.iterator();
		while(iterator.hasNext())
		{
			UserRole role = iterator.next();
			System.out.println(role);
		}
	}
	
	@Test
	public void testGetUserDetails(){
		
		UserDetails users = customUserDetailsService.loadUserByUsername("Jet");
		System.out.println(users.getUsername());
	}
	
	
}
