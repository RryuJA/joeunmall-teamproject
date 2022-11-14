package com.joeun.joeunmall.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.joeunmall.vo.OrderVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping
public class AdminOrderManageController {

	@GetMapping("/order.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "order");
		return "redirect:/admin/admin-orderManage.do";
//		return "demo";
	}

	@GetMapping("/admin/admin-orderManage.do")
	public String adminOrderManage(Model model) {
		log.info("admin-order");
		

		List<OrderVO> ordermanageList = new ArrayList<>();
		OrderVO ordermanageVO;
			
		ordermanageVO = new OrderVO("221101_001", "일승은", "파란티셔츠1", 
				22, 200000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderVO("221101_001", "이승은", "파란티셔츠2", 
				22, 200000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderVO("221101_001", "삼승은", "파란티셔츠3", 
				33, 300000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderVO("221101_001", "사승은", "파란티셔츠4", 
				44, 400000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderVO("221101_001", "오승은", "파란티셔츠5", 
				55, 500000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderVO("221101_001", "육승은", "파란티셔츠6", 
				66, 600000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderVO("221101_001", "칠승은", "파란티셔츠7", 
				77, 700000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderVO("221101_001", "팔승은", "파란티셔츠8", 
				88, 800000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		model.addAttribute("orderManageList", ordermanageList);
		
		return "/admin/admin-orderManage";
	}
	
	@GetMapping("/admin/admin-productRegistration.do")

	public String adminProductRegistration() {

		log.info("admin-productRegistration");

		return "/admin/admin-productRegistration";

	}
}
