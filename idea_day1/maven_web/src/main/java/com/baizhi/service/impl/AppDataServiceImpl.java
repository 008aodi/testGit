package com.baizhi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.AppDataDao;
import com.baizhi.entity.Artical;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Body;
import com.baizhi.entity.Header;
import com.baizhi.entity.SiArtical;
import com.baizhi.entity.SiDetail;
import com.baizhi.entity.User;
import com.baizhi.entity.UserData;
import com.baizhi.entity.WenBody;
import com.baizhi.entity.WenChapter;
import com.baizhi.entity.WenDetail;
import com.baizhi.service.AppDataService;
import com.baizhi.utils.MD5Utils;
@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class AppDataServiceImpl implements AppDataService {
	@Autowired
	private AppDataDao appDataDao;
	@Override
	public JSONObject findFirstPage(String type) {		
		if("all".equalsIgnoreCase(type)){
			List<Body> albums = appDataDao.findAlbum();
			List<Banner> banners = appDataDao.findBanner();						 
			ArrayList<Header> headers = new ArrayList< Header>();
			 for(Banner banner:banners){
				 Header bean = new Header();
				bean.setId(banner.getId());
				bean.setThumbnail(banner.getPicPath());
				bean.setDesc(banner.getPicName());
				headers.add(bean);
			}		
			List<Artical> articals = appDataDao.findArtical();
			JSONObject jb = new JSONObject();
			jb.put("artical", articals);			
			jb.put("album", albums);
			jb.put("header", headers);
			return jb;			
		}
		if("wen".equalsIgnoreCase(type)){
			List<WenBody> WenAlbums = appDataDao.findWenAlbum();
			JSONObject jb = new JSONObject();
			jb.put("body", WenAlbums);
			return jb;
		}
		if("si".equalsIgnoreCase(type)){
			List<SiArtical> articals = appDataDao.findSiArtical();
			JSONObject jb = new JSONObject();
			jb.put("artical", articals);
			return jb;
		}				
			return null;
	}
	@Override
	public JSONObject findDetail(String id, String uid) {		
		List<SiDetail> SiDetails = appDataDao.findSiDetail(id);
		JSONObject jb = new JSONObject();
		jb.put("artical",SiDetails );
		return jb;
	}
	@Override
	public JSONObject findWenDetail(String id, String uid) {
		WenDetail album = appDataDao.findWenDetail(id);
		List<WenChapter> chapters = appDataDao.findWenChapter(id);
		JSONObject jb = new JSONObject();
		jb.put("introduction", album);
		jb.put("list", chapters);
		return jb;
	}
	@Override
	public JSONObject findUser(String phone, String password) {
		JSONObject jb = new JSONObject();
		User findUser = appDataDao.findUser(phone);
		String salt = findUser.getSalt();
		String upPassword = MD5Utils.getPassword(password+salt);
		if(findUser.getPassword().equals(upPassword)){
			UserData findUserData = appDataDao.findUserData(phone);
			jb.put("成功", findUserData);
			return jb;
		}else{
			HashMap<String,String> map=	new HashMap<String,String>(); 
			map.put("error", "-200");
			map.put("errmsg", "密码错误");
			jb.put("失败", map);
			return jb;
		}		
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public JSONObject regist(String phone, String password) {
		JSONObject jb = new JSONObject();
		User findUser = appDataDao.findUser(phone);
		if(findUser==null){
			String salt = MD5Utils.getSalt();
			String userPassword = MD5Utils.getPassword(password+salt);
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			 Date creatTime= new Date();
			 User user = new User();
			 user.setCreatTime(creatTime);
			 user.setPassword(userPassword);
			 user.setSalt(salt);
			 user.setPhoneNum(phone);
			 user.setId(id);
			 user.setStatus(0);
			 appDataDao.regist(user);
			 UserData findUserData = appDataDao.findUserData(phone);
			 HashMap<String,String> map=new HashMap<String,String>();
			 map.put("password",findUserData.getPassword() );
			 map.put("uid",findUserData.getUid());
			 map.put("phone ",findUserData.getPhone());
			 jb.put("成功", map);
			return jb;
		}else{
			HashMap<String,String> map=	new HashMap<String,String>(); 
			map.put("error", "-200");
			map.put("errmsg", "该手机号已存在");
			jb.put("失败", map);
			return jb;			
		}
	}
	@Override
	public JSONObject modify(UserData userData) {
		JSONObject jb = new JSONObject();
		String uid = userData.getUid();
		User user = appDataDao.findUserByUid(uid);
		String phoneNum = user.getPhoneNum();
		User findUser = appDataDao.findUser(userData.getPhone());
		if(findUser==null){
			if(userData.getPassword()!=null||!"".equals(userData.getPassword())){						
				String salt = user.getSalt();
				String password = userData.getPassword();
				String password2 = MD5Utils.getPassword(password+salt);
				userData.setPassword(password2);			
			}
			appDataDao.updateUser(userData);
			UserData findUserData = appDataDao.findUserData(phoneNum);
			jb.put("成功", findUserData);
			return jb;			
		}else{
			HashMap<String,String> map=	new HashMap<String,String>(); 
			map.put("error", "-200");
			map.put("errmsg", "该手机号已存在");
			jb.put("失败", map);
			return jb;						
		}
		}	
}
