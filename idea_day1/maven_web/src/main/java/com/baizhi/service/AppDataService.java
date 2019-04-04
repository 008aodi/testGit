package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.UserData;

public interface AppDataService {
		public JSONObject findFirstPage(String type);
		public JSONObject findDetail(String id, String uid);
		public JSONObject findWenDetail(String id, String uid);
		public JSONObject findUser(String phone, String password);
		public JSONObject regist(String phone, String password);
		public JSONObject modify(UserData userData);
}
