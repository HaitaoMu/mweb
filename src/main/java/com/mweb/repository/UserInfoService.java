package com.mweb.repository;

import org.springframework.stereotype.Repository;

import com.mweb.model.UserInfo;

@Repository("UserInfoService")
public class UserInfoService extends AbstractDataService<UserInfo, String>{
	
	public UserInfoService() {
		setClazz(UserInfo.class);
	}
}
