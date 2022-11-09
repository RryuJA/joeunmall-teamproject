/**
 * 
 */
package com.joeun.joeunmall.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joeun.joeunmall.vo.OrderManageVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3
 *
 */
@Controller
@Slf4j
@RequestMapping
public class OrderManageController {
	
	@GetMapping("/orderManage.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "orderManage");
		return "redirect:/admin/admin-orderManage.do";
//		return "demo";
	}

	@GetMapping("/admin/admin-orderManage.do")
	public String adminOrderManage(Model model) {
		log.info("admin-orderManage");
		

		List<OrderManageVO> ordermanageList = new ArrayList<>();
		OrderManageVO ordermanageVO;
			
		ordermanageVO = new OrderManageVO("221101_001", "일승은", "파란티셔츠1", 
				22, 200000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderManageVO("221101_001", "이승은", "파란티셔츠2", 
				22, 200000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderManageVO("221101_001", "삼승은", "파란티셔츠3", 
				33, 300000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderManageVO("221101_001", "사승은", "파란티셔츠4", 
				44, 400000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderManageVO("221101_001", "오승은", "파란티셔츠5", 
				55, 500000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderManageVO("221101_001", "육승은", "파란티셔츠6", 
				66, 600000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderManageVO("221101_001", "칠승은", "파란티셔츠7", 
				77, 700000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		ordermanageVO = new OrderManageVO("221101_001", "팔승은", "파란티셔츠8", 
				88, 800000, Date.valueOf("2022-11-01"));
		ordermanageList.add(ordermanageVO);
		
		model.addAttribute("orderManageList", ordermanageList);
		
		return "/admin/admin-orderManage";
	}
}
