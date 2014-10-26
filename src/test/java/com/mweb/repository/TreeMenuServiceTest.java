/***********************************************************************
 *
 * @file         TreeMenuService.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月22日
 *
 *
 ***********************************************************************/
package com.mweb.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mweb.AbstractServiceTest;
import com.mweb.model.common.AbstractTreeNode;
import com.mweb.model.common.TreeMenuEntity;
import com.mweb.model.common.TreeMenuNode;

import static com.mweb.common.constats.Constants.ROOT_MENU;

/**
 * @author jet
 *
 */
public class TreeMenuServiceTest extends AbstractServiceTest
{
	private static TreeMenuNode ROOT = new TreeMenuNode();
	
	private static TreeMenuEntity MENU_ROOT = new TreeMenuEntity();
	
	@Autowired
	@Qualifier("MenuInfoService")
	MenuInfoService menuInfoService;
	
	@Autowired
	TreeMenuService treeMenuService;
	
	@Before
	public void init()
	{
//		Set<AbstractTreeNode> leaves = new HashSet<AbstractTreeNode>();
//		
//		ROOT.setChildrens(leaves);
	}
	
//	@Test
//	public void createRoot()
//	{
//		ROOT.setName("ROOT");
//		ROOT.setParent(null);
//		menuInfoService.save(ROOT);
//	}
	
	
	@Test
	public void createMenu()
	{
		MENU_ROOT = new TreeMenuEntity(ROOT_MENU,"home",ROOT_MENU);
		MENU_ROOT.setParent(null);


		TreeMenuEntity menuInterface = new TreeMenuEntity("Interface Manager","#","fa fa-cogs fa-fw");
		menuInterface.setParent(MENU_ROOT);

		TreeMenuEntity menuDatabase = new TreeMenuEntity("Database Manager","dbConfigIndex","fa fa-files-o fa-fw");
		menuDatabase.setParent(MENU_ROOT);
		
		TreeMenuEntity menuUser = new TreeMenuEntity("User Manager","listUser","fa fa-file fa-fw");
		menuUser.setParent(MENU_ROOT);
		
		TreeMenuEntity menuRole = new TreeMenuEntity("Role Manager","listRole","fa fa-file fa-fw");
		menuRole.setParent(MENU_ROOT);

		TreeMenuEntity menuChart = new TreeMenuEntity("Chart","chart","fa fa-cogs fa-fw");
		menuChart.setParent(MENU_ROOT);
		
		TreeMenuEntity menuList = new TreeMenuEntity("Menu Manager","menu","fa fa-cogs fa-fw");
		menuList.setParent(MENU_ROOT);
		
		TreeMenuEntity menuSapInterface = new TreeMenuEntity("Sap Interface","sapIndex","fa fa-cogs fa-fw");
		menuSapInterface.setParent(menuInterface);
		
		Set<TreeMenuEntity> leaves = new HashSet<TreeMenuEntity>();
		leaves.add(menuInterface);
		leaves.add(menuDatabase);
		leaves.add(menuUser);
		leaves.add(menuRole);
		leaves.add(menuChart);
		leaves.add(menuList);
		
		MENU_ROOT.setChildrens(leaves);
		treeMenuService.save(MENU_ROOT);
	    treeMenuService.save(menuSapInterface);
		
	}
	
	@Test
	public void iteratorMenu(){
	
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("name", ROOT_MENU);
		MENU_ROOT = treeMenuService.findByParameter(parameters).get(0);
		System.out.println(MENU_ROOT.createTreeMenu(MENU_ROOT));
	}
	
	
	@Test
	public void getMenu()
	{
		List<TreeMenuEntity> menus = treeMenuService.findSubMenuById(41);
		for(TreeMenuEntity entity : menus)
		{
			System.out.println(entity);
		}
	}
	
	@Test
	public void addMenu()
	{
		
		TreeMenuEntity menuRandomChart = new TreeMenuEntity("RandomChart","<c:url value='/randomchart' />","fa fa-cogs fa-fw");
		treeMenuService.addSubMenu(menuRandomChart, 41);
		
		Assert.assertEquals(treeMenuService.findSubMenuById(41).size(), 2);
	}
	
	public void parseTreeMenu(TreeMenuEntity menu)
	{
		if(null!=menu)
		{
			for(TreeMenuEntity entity : menu.getChildrens())
			{
				parseTreeMenu(entity);
			}
		}
	}
}
