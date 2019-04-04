package com.baizhi.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	@Override
	public Map<String,Object> login(String name,String password,HttpSession session) {				
		Admin admin = adminDao.findAdmin(name);
		Map<String,Object> map = new HashMap<String,Object>();
		if(admin==null){
			map.put("mess","用户名不存在，请重新输入");
		}else if(admin.getPassword().equals(password)){
			session.setAttribute("admin", admin);
			map.put("mess",null);
		}else{
			map.put("mess", "您输入的密码错误");
		}
		return map;
	}
	@Override
	public void updateAdmin(Admin useradmin,HttpSession session) {
		Admin admin = (Admin)session.getAttribute("admin");
		admin.setPassword(useradmin.getPassword());
		session.setAttribute("admin",admin);
		adminDao.updateAdmin(admin);
	}
	@Override
	public Map<String, Object> checkPwd(String password, HttpSession session) {
		Admin admin = (Admin)session.getAttribute("admin");		
		Map<String,Object> map = new HashMap<String,Object>();
		if(admin.getPassword().equals(password)){
			map.put("mess",null);			
		}else{
			map.put("mess", "您输入的密码错误");
		}
		return map;
	}
		
}
