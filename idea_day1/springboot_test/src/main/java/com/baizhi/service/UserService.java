package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    public Map<String,Object> login(User user,HttpSession session);
    public void regist(User user);

}
