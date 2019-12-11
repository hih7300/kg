package com.team404.util.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginFormIntercepter extends HandlerInterceptorAdapter{

	// 로그인 성공후 post핸들러	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//로그인 성공시 생성되는 세션 user_id
		String user_id = (String) request.getSession().getAttribute("user_id");
		String uri = (String) request.getSession().getAttribute("uri");
		
		// 1. 로그인 성공, 기존에 접근하려는 URI가 있는 경우
		if(uri != null && user_id != null) {
			response.sendRedirect(uri);
		
		// 2. 일반적인 로그인 성공
		} else if(user_id != null) {
			response.sendRedirect(request.getContextPath());
		
		//3. 로그인 실패인 경우, 기존의 컨트롤러 대로 실행됨.
		} 
		
		
	}
	
	

}
