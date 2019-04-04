package com.baizhi.dao;

import java.util.List;

import com.baizhi.entity.Artical;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Body;
import com.baizhi.entity.SiArtical;
import com.baizhi.entity.SiDetail;
import com.baizhi.entity.User;
import com.baizhi.entity.UserData;
import com.baizhi.entity.WenBody;
import com.baizhi.entity.WenChapter;
import com.baizhi.entity.WenDetail;

public interface AppDataDao {
		public List<Banner> findBanner();
		public List<Body> findAlbum();
		public List<Artical> findArtical();
		//获取闻界面的专辑
		public List<WenBody> findWenAlbum();
		//获取思界面文章
		public List<SiArtical> findSiArtical();
		//获取思详情
		public List<SiDetail> findSiDetail(String id);
		//获取闻详情
		public WenDetail findWenDetail(String id);
		//根据id查询所有章节
		public List<WenChapter> findWenChapter(String id);
		//根据手机号获取用户
		public User findUser(String phone);
		//获取用户数据UserData响应给前段
		public UserData findUserData(String phone);
		//用户注册
		public void regist(User user);
		//修改用户信息
		public void updateUser(UserData userData);
		//根据用户id查用户
		public User findUserByUid(String uid);
		
}
