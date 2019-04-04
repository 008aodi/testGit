package com.baizhi.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baizhi.entity.Admin;

public interface AdminService {
	public Map<String,Object> login(String name, String password, HttpSession session);
	public void updateAdmin(Admin admin, HttpSession session);
	public Map<String, Object> checkPwd(String password, HttpSession session);
}
