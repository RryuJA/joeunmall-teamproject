package com.joeun.joeunmall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ControllerParamException {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400 http error
	public String handlerMssingParams(MissingServletRequestParameterException e, Model model) {
	
		log.info("인자 예외처리 : " + e);
		String name = e.getParameterName();
		    
		String msg = name + " 인자가 전송되지 않았습니다";
		log.error("msg : " + msg);
		
		model.addAttribute("errMsg", msg);
		model.addAttribute("movePath", "/admin/admin-orderManage.do");
				
		return "/error/error";
	}
}
