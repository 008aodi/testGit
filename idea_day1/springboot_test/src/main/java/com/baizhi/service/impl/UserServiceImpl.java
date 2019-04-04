package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    private UserDao userDao;
    @Override
    public Map<String,Object> login(User user,HttpSession session) {
        User upUser = userDao.findUserByName(user.getUsername());
        HashMap<String,Object> map = new HashMap<>();
        if (upUser==null){
            map.put("mess","用户名不存在，请重新输入");
        }else if(upUser.getPassword().equals(user.getPassword())){
            session.setAttribute("user", upUser);
            map.put("mess",null);
        }else{
            map.put("mess", "您输入的密码错误");
        }
        return map;
    }

    @Override
    public void regist(User user) {

    }
}
