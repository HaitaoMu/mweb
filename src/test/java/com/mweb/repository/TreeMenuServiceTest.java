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
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mweb.AbstractServiceTest;
import com.mweb.model.common.AbstractTreeNode;
import com.mweb.model.common.TreeMenuEntity;
import com.mweb.model.common.TreeMenuNode;

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
	public void createChildren()
	{
		ROOT.setName("ROOT");
		ROOT.setParent(null);
		menuInfoService.save(ROOT);
		
		TreeMenuNode nodeone = new TreeMenuNode();
		nodeone.setChildrens(null);
		nodeone.setMenuUrl("/home");
		nodeone.setName("home");
		nodeone.setParent(ROOT);

		TreeMenuNode nodetwo = new TreeMenuNode();
		nodetwo.setChildrens(null);
		nodetwo.setMenuUrl("/index");
		nodetwo.setName("index");
		nodetwo.setParent(ROOT);
		
		Set<AbstractTreeNode> leaves = new HashSet<AbstractTreeNode>();
		leaves.add(nodeone);
		leaves.add(nodetwo);
		
		ROOT.setChildrens(leaves);
		menuInfoService.update(ROOT);
	}
	
	@Test
	public void createMenu()
	{
		MENU_ROOT = new TreeMenuEntity("ROOT","/","ROOT MENU");
		MENU_ROOT.setParent(null);
		
		TreeMenuEntity menuHome = new TreeMenuEntity("home","/home","/home url");
		TreeMenuEntity menuUser = new TreeMenuEntity("user","/user","/user url");
		
		Set<TreeMenuEntity> leaves = new HashSet<TreeMenuEntity>();
		leaves.add(menuHome);
		leaves.add(menuUser);
		
		MENU_ROOT.setChildrens(leaves);
		treeMenuService.save(MENU_ROOT);

	}
	
	@Test
	public void iteratorMenu(){
	
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("name", "ROOT");
		MENU_ROOT = treeMenuService.findByParameter(parameters).get(0);
		parseTreeMenu(MENU_ROOT);
	}
	
	public void parseTreeMenu(TreeMenuEntity menu){
		if(null!=menu)
		{
			System.out.println(menu.getMenuUrl() +"-"+menu.getName());
			for(TreeMenuEntity entity : menu.getChildrens())
			{
				parseTreeMenu(entity);
			}
		}
	}
}
