package com.mweb.repository;

import org.hibernate.criterion.ProjectionList;
import org.springframework.stereotype.Repository;

import com.mweb.model.UserRole;

@Repository("UserRoleService")
public class UserRoleService extends AbstractDataService<UserRole, String> {

	public UserRoleService() {
		setClazz(UserRole.class);
	}

}
