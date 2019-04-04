package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        List<User> all = userDao.findAll();
        return all;
    }
}
