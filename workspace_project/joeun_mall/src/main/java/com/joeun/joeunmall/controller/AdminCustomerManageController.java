package com.joeun.joeunmall.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.joeunmall.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author team3
 * 
 * */

@Slf4j
@Controller
@RequestMapping
public class AdminCustomerManageController {

	@GetMapping("/customerManage.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "customerManage");
		return "redirect:/admin/admin-customerManage.do";
	}
	
	@GetMapping("/admin/admin-customerManage.do")
	public String adminCustomerManage(Model model) {
		log.info("admin-customerManage");
		
		List<UserVO> userList = new ArrayList<>();
		UserVO userVO;
		
		userVO = new UserVO("2022001", "일승은", Date.valueOf("2022-11-02"), 10, 
				"남", "010-1234-5678");
		userList.add(userVO);
		
		userVO = new UserVO("2022001", "이승은", Date.valueOf("2022-11-02"), 20, 
				"남", "010-1234-5678");
		userList.add(userVO);
		
		userVO = new UserVO("2022001", "삼승은", Date.valueOf("2022-11-02"), 30, 
				"여", "010-1234-5678");
		userList.add(userVO);
		
		userVO = new UserVO("2022001", "사승은", Date.valueOf("2022-11-02"), 40, 
				"남", "010-1234-5678");
		userList.add(userVO);
		
		userVO = new UserVO("2022001", "오승은", Date.valueOf("2022-11-02"), 50, 
				"여", "010-1234-5678");
		userList.add(userVO);
		
		userVO = new UserVO("2022001", "육승은", Date.valueOf("2022-11-02"), 60, 
				"남", "010-1234-5678");
		userList.add(userVO);
		
		userVO = new UserVO("2022001", "칠승은", Date.valueOf("2022-11-02"), 70, 
				"여", "010-1234-5678");
		userList.add(userVO);
		
		userVO = new UserVO("2022001", "팔승은", Date.valueOf("2022-11-02"), 80, 
				"남", "010-1234-5678");
		userList.add(userVO);
		
		model.addAttribute("userList", userList);
		
		return "/admin/admin-customerManage";
	}
	
}
