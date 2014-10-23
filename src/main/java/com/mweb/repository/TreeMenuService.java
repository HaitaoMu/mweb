package com.mweb.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mweb.model.common.TreeMenuEntity;
import com.mweb.model.common.TreeMenuNode;

@Repository
public class TreeMenuService extends AbstractDataService<TreeMenuEntity, Integer> {

	public TreeMenuService()
	{
		setClazz(TreeMenuEntity.class);
	}
	
	public List<TreeMenuEntity> findSubMenuById(int Id)
	{
		Map parameter = new HashMap<String,String>();
		parameter.put("parent.id", Id);
		return findByParameter(parameter);
	}
	
	public void addSubMenu(TreeMenuEntity entity,int Id)
	{
		TreeMenuEntity root = findOne(Integer.valueOf(Id));
		if(null!=root)
		{
			entity.setParent(root);
			save(entity);
		}
	}
	
	
}
