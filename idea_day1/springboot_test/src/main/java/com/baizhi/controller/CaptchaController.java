package com.baizhi.controller;


import com.baizhi.util.CreateValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/capt")
@Controller
public class CaptchaController {
	@RequestMapping("/getcapt")
	public void getCaptcha(HttpSession session, HttpServletResponse response) throws IOException {
		//创建验证码工具类对象
		CreateValidateCode ValidateCode = new CreateValidateCode();
		//获取验证码
		String code = ValidateCode.getCode();
		//session存入code
		session.setAttribute("code", code);
		//获取输出流
		ServletOutputStream out = response.getOutputStream();
		//将验证码通过输出流返回
		ValidateCode.write(out);
	} 

}
