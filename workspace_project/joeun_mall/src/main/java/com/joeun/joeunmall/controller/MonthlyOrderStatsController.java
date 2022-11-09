package com.joeun.joeunmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MonthlyOrderStatsController {
	@GetMapping("/admin-orderStatsMonthly.do")
	public String orderStatsMonthly() {
		return "/admin/admin-orderStatsMonthly";
	}
}
