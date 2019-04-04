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
		//������֤�빤�������
		CreateValidateCode ValidateCode = new CreateValidateCode();
		//��ȡ��֤��
		String code = ValidateCode.getCode();
		//session����code
		session.setAttribute("code", code);
		//��ȡ�����
		ServletOutputStream out = response.getOutputStream();
		//����֤��ͨ�����������
		ValidateCode.write(out);
	} 

}
