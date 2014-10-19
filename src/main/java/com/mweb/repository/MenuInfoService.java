package com.mweb.repository;

import org.springframework.stereotype.Repository;

import com.mweb.model.Menu;

@Repository("MenuInfoService")
public class MenuInfoService extends AbstractDataService<Menu, String> {

	public MenuInfoService()
	{
		setClazz(Menu.class);
	}
}
