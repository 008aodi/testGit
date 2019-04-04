package com.baizhi.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.utils.CreateValidateCode;

@RequestMapping("/capt")
@Controller
public class CaptchaController {
	@RequestMapping("/getcapt.do")
	public void getCaptcha(HttpSession session,HttpServletResponse response) throws IOException{
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
