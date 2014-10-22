package com.mweb.repository;

import static com.mweb.common.constats.Constants.DESTINATION_DB;
import static com.mweb.common.constats.Constants.SOURCE_DB;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import com.mweb.AbstractServiceTest;
import com.mweb.common.constats.DBType;
import com.mweb.model.DBConfig;
import com.mweb.model.PageResult;
import com.mweb.model.UserInfo;
import com.mweb.model.UserRole;
import com.mweb.model.plugin.PK;
import com.mweb.model.plugin.SAPEntity;
import com.mweb.repository.plugin.SAPService;
import com.mweb.repository.security.dao.CustomUserDetailService;

public class RepositoryServiceTest extends AbstractServiceTest {
	
	private static final String SALT = null;
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	CustomUserDetailService customUserDetailsService;
	
	@Autowired
	SAPService sapService;
	
	@Autowired
	DBConfigService dbconfigService;
	
	ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
	SecureRandom random = new SecureRandom();
	
	@Test
	public void testSaveUser(){
	
		UserInfo user = new UserInfo();
		user.setUserName("Jet");
		user.setUserPassword(passwordEncoder.encodePassword("123456", SALT));
		user.setUserDescription("User for test");
		
		UserInfo userone = new UserInfo();
		userone.setUserName("Bruce");
		userone.setUserPassword(passwordEncoder.encodePassword("123456", SALT));
		userone.setUserDescription("User for test");
	
	
		UserRole role = new UserRole();
		role.setRoleDescription("Role for test");
		role.setRoleName("USER");
		
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(role);
		
		user.setRoles(roles);
		userone.setRoles(roles);
		userInfoService.save(userone);
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
	public void testGetByPage()
	{
		PageResult page = userInfoService.findByPage(2, 2);
		for(Object user :page.getRows()){
			System.out.println(user);
		}
	}
	
	@Test
	public void testGetUserDetails(){
		
		UserDetails users = customUserDetailsService.loadUserByUsername("Jet");
		System.out.println(users.getUsername());
	}
	
	
	@Test
	public void saveDbConfigService()
	{
		DBConfig sourceConfig = new DBConfig();
		sourceConfig.setDbName(SOURCE_DB);
		sourceConfig.setDbType(DBType.ORACLE);
		sourceConfig.setDriver("oracle.jdbc.OracleDriver");
		sourceConfig.setPassword("12345678");
		sourceConfig.setUsername("source");
		sourceConfig.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

		
		DBConfig destConfig = new DBConfig();
		destConfig.setDbName(DESTINATION_DB);
		destConfig.setDbType(DBType.ORACLE);
		destConfig.setDriver("oracle.jdbc.OracleDriver");
		destConfig.setPassword("12345678");
		destConfig.setUsername("destination");
		destConfig.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		dbconfigService.save(sourceConfig);
		dbconfigService.save(destConfig);
	}
	
	@Test
	public void testGetSAP()
	{
		PK pk = new PK();
		pk.setPropertyCode("SAP1");
		pk.setUserId("ABCDEFG");
		SAPEntity entity = sapService.findOne(pk);
		System.out.println(entity);
	}
	
	@Test
	public void saveSAPEntity(){
		
		PK pk = new PK();
		pk.setUserId("ABCDEFG");
		for (int i = 0; i < 500; i++)
		{
			pk.setPropertyCode("SAP"+i);
			SAPEntity entity = new SAPEntity();
			entity.setPk(pk);
			entity.setAmount(random.nextDouble());
			entity.setPrice(random.nextDouble());
			entity.setVolume(random.nextDouble());
			sapService.save(entity);
		}
	}
	
}
