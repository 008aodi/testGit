package com.baizhi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baizhi.entity.Admin;


public class UserFilter implements Filter{

	@Override
	public void destroy() {		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest request1 = (HttpServletRequest)request;
			HttpServletResponse response1 = (HttpServletResponse)response;
	    	HttpSession session = request1.getSession();
			Admin admin = (Admin)session.getAttribute("admin");
			if(admin!=null||"".equals(admin)){
				chain.doFilter(request,response);  
			}else{
				response1.sendRedirect("/cmfz/login.jsp");
			}		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
