/***********************************************************************
 *
 * @file         CustomUserDetailService.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年9月29日
 *
 *
 ***********************************************************************/
package com.mweb.repository.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mweb.model.UserInfo;
import com.mweb.model.UserRole;
import com.mweb.repository.UserInfoService;

/**
 * @author jet
 *
 */
//@Service("customUserDetailsService")
@Component
public class CustomUserDetailService implements UserDetailsService
{
	
	private static final int ZERO = 0;
	@Autowired
	public UserInfoService userInfoService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("userName", username);
		List<UserInfo> users = userInfoService.findByParameter(parameters);
		if(null!=users && users.size() > ZERO)
			return createUser(users.get(0));

		return null;
	}

	private User createUser(UserInfo userInfo)
	{
		Set<GrantedAuthority> authorities = null;
		Assert.notNull(userInfo, "user is empty");

		authorities = createAuthority(userInfo.getRoles());
		return new User(userInfo.getUserName(), userInfo.getUserPassword(), authorities);
	}

	private Set<GrantedAuthority> createAuthority(Set<UserRole> roles)
	{
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		Assert.notNull(roles, "user role set is empty");

		Iterator<UserRole> iterator = roles.iterator();
		while (iterator.hasNext())
		{
			UserRole userRole = (UserRole) iterator.next();
			GrantedAuthority authority = convertUserRoleToAuthorities(userRole);
			if (null != authority && !authorities.contains(authority))
			{
				authorities.add(authority);
			}
		}

		return authorities;
	}

	private GrantedAuthority convertUserRoleToAuthorities(UserRole role)
	{
		if (null != role)
		{
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
			return authority;
		}
		return null;

	}
}
