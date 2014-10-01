package com.mweb.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;






import com.mweb.config.common.ApplicationConfiguration;
import com.mweb.model.UserInfo;
import com.mweb.model.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class RepositoryServiceTest {
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Test
	public void testSaveUser(){
		UserInfo user = new UserInfo();
		user.setUserName("Jet");
		user.setUserPassword("123456");
		user.setUserDescription("User for test");
	
	
		UserRole role = new UserRole();
		role.setRoleDescription("Role for test");
		role.setRoleName("Administrator");
		
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(role);
		
		user.setRoles(roles);
		userInfoService.save(user);
	}
}
