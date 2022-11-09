package com.joeun.joeunmall.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminInterceptor extends HandlerInterceptorAdapter {	
	/*
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		log.info("로그인 점검:"+ session.getAttribute("SESS_LOGIN_ID"));

		boolean result=false;
		
		if(session.getAttribute("SESS_LOGIN_ID")==null) {//로그인 안했을때
			log.info("로그인 안됨");
			response.sendRedirect(request.getContextPath()+"/admin/admin-login.do");
			result = false;
		} else {//로그인 했을때
			String sessId = session.getAttribute("SESS_LOGIN_ID").toString();
			if(sessId.equals("admin")== false || (!id.equals("admin") || !pw.equals("joeun1234"))) {//관리자가 아닐때
				log.info("관리자가 아님");
				//response.sendRedirect(request.getContextPath()+"/admin/admin-login.do");
				request.setAttribute("err_msg", "관리자 권한이 없습니다.");
				RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath() + "/admin/admin-login");
				result = false;
			} else {
				log.info("관리자");
				result = true;
			}
		}
		
		return result;
	}*/
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("로그인 선처리");
		boolean result = false;
		
		HttpSession session = request.getSession();
		
		// 인증(authentication)
		if (session.getAttribute("SESS_LOGIN_ID") == null) {
			
			log.info("로그인 미인증");
			response.sendRedirect(request.getContextPath() + "/admin/admin-login.do");
			
			result = false;
			
		} else { // 로그인 인증시
			
			String sessionId = session.getAttribute("SESS_LOGIN_ID").toString();
			
			// 인가(authorization) 
			if (sessionId.equals("admin")) {
					
				log.info("관리자");
				result = true;
				
			} else {
				log.info("일반 사용자");
				// 403 에러 유발 : 권한 없음 관련 Http Error
				
				response.sendError(403, "관리자 권한이 없습니다");
				// response.sendError(HttpStatus.FORBIDDEN, "관리자 권한이 없습니다");
				result = false;
			}
			
		} //
		
		return result;
	}

}
