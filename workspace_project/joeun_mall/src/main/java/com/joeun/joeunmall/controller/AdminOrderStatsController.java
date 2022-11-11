package com.joeun.joeunmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping
public class AdminOrderStatsController{
	@GetMapping("/orderStats.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "orderStats");
		return "redirect:/admin/admin-orderStats.do";
	}
	
	@GetMapping("/admin/admin-orderStats.do")
	public String adminOrderStats(Model model) {
		log.info("admin-orderStats.do");
		
		return "/admin/admin-orderStats";
	}
	
	@GetMapping("/admin/admin-orderStatsGraph.do")
	public String orderStatsGraph() {
		log.info("admin-orderStatsGraph.do");
		return "/admin/admin-orderStatsGraph";
	}
	
	
				
	@GetMapping("/admin/admin-orderStatsMonthly.do")
	public String orderStatsMonthly() {
		log.info("admin-orderStatsMonthly.do");
		return "/admin/admin-orderStatsMonthly";
		
	}
		
}
