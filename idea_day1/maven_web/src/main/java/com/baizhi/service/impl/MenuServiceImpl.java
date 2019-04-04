package com.baizhi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class MenuServiceImpl implements MenuService{
		
	@Autowired
	private MenuDao menuDao;	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Menu> findAllMenu() {
		List<Menu> menus = menuDao.findAllMenu();
		return menus;
	}

}
