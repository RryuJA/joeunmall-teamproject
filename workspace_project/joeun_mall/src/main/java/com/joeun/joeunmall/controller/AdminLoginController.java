package com.joeun.joeunmall.controller;


import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3
 *
 */
@Controller
@Slf4j
public class AdminLoginController {

	@GetMapping("/")
	public String demo(Model model) {
		log.info("demo");
		// model.addAttribute("demo", "team3");
		//return "redirect:/admin/admin-productManage.do";
		return  "redirect:/admin/admin-login.do";
//		return "demo";
	}

/*	@GetMapping("/mypage.do")
	public String mypage() {
		log.info("mypage");
		return "mypage";
	}*/
	
	@GetMapping("/admin/admin-login.do")
	public String adminLogin() {
		log.info("admin-login");
		return "/admin/admin-login";
	}
	
	@PostMapping("/admin/admin-loginProc.do")
	public String adminLoginProc(@RequestParam(value = "id",required = true) String id, 
		@RequestParam(value = "pw", required = true) String pw, 
		 Model model,HttpSession session) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPw = passwordEncoder.encode("joeun1234");
		boolean checkAuth = passwordEncoder.matches(pw.trim(), hashedPw);
		
		//관리자 인지 점검
		if ((id.trim().equals("admin") && checkAuth == true) ||  // 관리자
				(id.trim().equals("user") && checkAuth == true)) // 일반 회원
		{
			log.info("회원&관리자");
			//세션 생성
			if (session.getAttribute("SESS_LOGIN_ID")==null){
				session.setAttribute("SESS_LOGIN_ID", id);
			}
			return "redirect:/admin/admin-orderManage.do";	//나중에 회원이 갈 수 있는 페이지로 바꿔주면 댐 
		
		} else if ((id.trim().equals("admin") && checkAuth == false) ||  // 관리자
				(id.trim().equals("user") && checkAuth == false)) // 일반 회원{
		{
			model.addAttribute("err_msg", "패스워드가 일치하지 않습니다.");
			return "/admin/admin-login";
			
		} else { 
			model.addAttribute("err_msg", "회원이 아닙니다.");
			return "/admin/admin-login";
		}
	}
	
	/* 메인페이지 로그아웃 */
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logoutMainGET(HttpSession session) throws Exception{
		
		log.info("로그아웃");
		if(session.getAttribute("SESS_LOGIN_ID")!=null) {
			session.invalidate();
		}
		return "redirect:/admin/admin-login.do";
	}
	
	
}
