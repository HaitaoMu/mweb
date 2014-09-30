package com.mweb.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import com.mweb.config.common.ApplicationConfiguration;
import com.mweb.model.UserInfo;
import com.mweb.model.UserRole;
import com.mweb.model.UserWithRole;

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
		userInfoService.save(user);
	
		UserRole role = new UserRole();
		role.setRoleDescription("Role for test");
		role.setRoleName("Administrator");
		userRoleService.save(role);
		
		List<UserWithRole> relations = new ArrayList<UserWithRole>();
	}
}
