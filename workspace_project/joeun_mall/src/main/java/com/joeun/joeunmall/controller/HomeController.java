package com.joeun.joeunmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@RequestMapping("/")	// /는 root를 말함(context-path가 가장 첫번쨴데 거기임(root))
	public String home(Model model) {	// String은 jsp를 의미(나중엔 다른 미디어가 될 수도 있음 기본적으론 jsp)
		log.info("home");
//		return "home";	// servlet-context의 view resolver에서 확장자 .jsp가 생략되도 jsp 확장자로 설정되도록 설정되었기 대문에 home만 쳐도 home.jsp로 파일로 이동함
		return "/admin/admin-orderStatsGraph";
	}
	
}
