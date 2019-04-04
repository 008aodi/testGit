package com.baizhi.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;


import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@RequestMapping("/login.do")
	@ResponseBody
	public Map login(String name,String password,HttpSession session){						
		Map<String, Object> map = adminService.login(name,password,session);
		return map;
	}
	@RequestMapping("/code.do")
	@ResponseBody
	public boolean validateCode(String enCode,HttpSession session){
		String code = (String)session.getAttribute("code");
		if(code.equals(enCode)){
			return true;
		}else{
			return false;
		}		
	}
	@RequestMapping("/exit.do")
	public String exit(HttpSession session){
		session.invalidate();
		return "redirect:/login.jsp";
	}
	@RequestMapping("/updatePwd.do")
	@ResponseBody
	public String updatePassword(Admin admin,HttpSession session){
		adminService.updateAdmin(admin,session);
		return "ok";
	}
	@RequestMapping("/checkPwd.do")
	@ResponseBody
	public Map<String,Object> checkPwd(String password,HttpSession session){						
		Map<String,Object> map = adminService.checkPwd(password,session);
		return map;
	}
}
