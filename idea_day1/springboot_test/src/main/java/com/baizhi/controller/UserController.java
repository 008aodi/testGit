package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(User user,HttpSession session){
        Map<String, Object> map = userService.login(user, session);
        return map;
    }
    @RequestMapping("regist")
    public String regist(User user){
        userService.regist(user);
        return "main";
    }
    @RequestMapping("code")
    @ResponseBody
    public boolean validateCode(HttpSession session,String eCode){
        String code = (String) session.getAttribute("code");
        if(code.equalsIgnoreCase(eCode)){
            return true;
        }else{
            return false;
        }
    }
}
