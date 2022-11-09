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

import com.joeun.joeunmall.vo.OrderstatsVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3
 *
 */
@Controller
@Slf4j
@RequestMapping

public class OrderStatsController {

	@GetMapping("/orderStats.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "orderStats");
		return "redirect:/admin/admin-orderStats.do";
	}
	
	@GetMapping("/admin/admin-orderStats.do")
	public String adminOrderStats(Model model) {
		log.info("admin-orderStats");
		
		return "/admin/admin-orderStats";
	}
	
}




















