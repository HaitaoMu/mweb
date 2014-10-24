package com.mweb.repository;

import org.hibernate.criterion.ProjectionList;
import org.springframework.stereotype.Repository;

import com.mweb.model.UserInfo;

@Repository
public class UserInfoService extends AbstractDataService<UserInfo, String>{
	
	public UserInfoService() {
		setClazz(UserInfo.class);
	}

	
}
