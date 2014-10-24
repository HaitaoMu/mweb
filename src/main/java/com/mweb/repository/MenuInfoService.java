package com.mweb.repository;

import org.hibernate.criterion.ProjectionList;
import org.springframework.stereotype.Repository;

import com.mweb.model.common.TreeMenuNode;

@Repository("MenuInfoService")
public class MenuInfoService extends AbstractDataService<TreeMenuNode, String> {

	public MenuInfoService()
	{
		setClazz(TreeMenuNode.class);
	}

}
