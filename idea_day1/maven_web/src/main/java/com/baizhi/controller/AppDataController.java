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
	 * @˼��������ӿڲ�������id���û�id
	 */
	@RequestMapping("/detail.do")
	@ResponseBody
	public JSONObject getDetail(String id,String uid){
		JSONObject jb = appDataService.findDetail(id, uid);
		return jb;
	}
	/**
	 * @��ר������ӿ�
	 */
	@RequestMapping("/detail/wen.do")
	@ResponseBody
	public JSONObject getWenDetail(String id,String uid){
		JSONObject jb = appDataService.findWenDetail(id, uid);
		return jb;
	}
	/**
	 * @��¼�ӿ�
	 */
	@RequestMapping("/account/login.do")
	@ResponseBody
	public JSONObject login(String phone,String password){
		JSONObject userJB = appDataService.findUser(phone, password);
		return userJB;		
	}
	/**
	 * @ע��ӿ�
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
