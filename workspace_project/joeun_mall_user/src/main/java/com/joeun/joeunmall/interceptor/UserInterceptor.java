package com.joeun.joeunmall.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.joeun.joeunmall.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserInterceptor extends HandlerInterceptorAdapter {	
	
	@Autowired UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("로그인 선처리(사용자)");
		boolean result = false;
		
		HttpSession session = request.getSession();
		
		// 인증(authentication)
		if (session.getAttribute("SESS_LOGIN_ID") == null) {
			
			log.info("로그인 미인증");
			request.setAttribute("errMsg", "로그인 하십시오");
			request.setAttribute("movePath", "/user/user-login.do");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/error/error.jsp");
			rd.forward(request, response);
			//response.sendRedirect(request.getContextPath() + "/user/user-login.do");
			
			result = false;
			
		} else { // 로그인 인증시
			
			// 인가(authorization) 
			result = true;
		}	
		
		return result;
	}

}
