package com.joeun.joeunmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AllOrderStatsGraphController {
	@GetMapping("/admin-orderStatsGraph.do")
	public String orderStatsGraph(Model model) {
		return "/admin/admin-orderStatsGraph";
	}
}
