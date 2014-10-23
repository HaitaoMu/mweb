package com.mweb.repository;

import org.springframework.stereotype.Repository;

import com.mweb.model.common.TreeMenuEntity;
import com.mweb.model.common.TreeMenuNode;

@Repository
public class TreeMenuService extends AbstractDataService<TreeMenuEntity, String> {

	public TreeMenuService()
	{
		setClazz(TreeMenuEntity.class);
	}
}
