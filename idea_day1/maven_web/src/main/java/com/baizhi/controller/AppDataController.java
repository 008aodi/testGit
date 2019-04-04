package com.baizhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.UserData;
import com.baizhi.service.AppDataService;

@Controller
@RequestMapping("/app")
public class AppDataController{
	@Autowired
	private AppDataService  appDataService;
	@RequestMapping("/first_page.do")
	@ResponseBody
	public JSONObject getFirstPage(String type,String uid){
		JSONObject jb = appDataService.findFirstPage(type);	
		return jb;
	}
	/**
	 * @思文章详情接口参数文章id和用户id
	 */
	@RequestMapping("/detail.do")
	@ResponseBody
	public JSONObject getDetail(String id,String uid){
		JSONObject jb = appDataService.findDetail(id, uid);
		return jb;
	}
	/**
	 * @闻专辑详情接口
	 */
	@RequestMapping("/detail/wen.do")
	@ResponseBody
	public JSONObject getWenDetail(String id,String uid){
		JSONObject jb = appDataService.findWenDetail(id, uid);
		return jb;
	}
	/**
	 * @登录接口
	 */
	@RequestMapping("/account/login.do")
	@ResponseBody
	public JSONObject login(String phone,String password){
		JSONObject userJB = appDataService.findUser(phone, password);
		return userJB;		
	}
	/**
	 * @注册接口
	 */
	@RequestMapping("/account/regist.do")
	@ResponseBody
	public JSONObject regist(String phone,String password){
		JSONObject regist = appDataService.regist(phone, password);
		return regist;
	}
	@ResponseBody
	@RequestMapping("/account/modify.do")
	public JSONObject modify(UserData userData){
		JSONObject modify = appDataService.modify(userData);
		return modify;
	}
}
