package com.joeun.joeunmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3
 *
 */
@Controller
@Slf4j
@RequestMapping

public class OrderStatsController {

//	@GetMapping("/orderStats.do")
//	public String demo(Model model) {
//		log.info("demo");
//		model.addAttribute("admin", "orderStats");
//		return "redirect:/admin/admin-orderStats.do";
//	}
	@GetMapping("/admin-orderStats.do")
	public String adminOrderStats(Model model) {
		log.info("admin-orderStats");
		return "/admin/admin-orderStats";
	}
	
}




















