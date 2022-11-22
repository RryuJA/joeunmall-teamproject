package com.joeun.joeunmall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.joeunmall.service.OrderManageService;
import com.joeun.joeunmall.vo.OrderVO;
import com.joeun.joeunmall.vo.PageDTO;
import com.joeun.joeunmall.vo.PageMaker;

import lombok.extern.slf4j.Slf4j;

/**
 * @author team3 LSE
 * 
 * */
@Controller
@Slf4j
@RequestMapping
public class AdminOrderManageController {
	
	@Autowired
	OrderManageService orderManageService; 

	@GetMapping("/orderManage.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "orderManage");
		return "redirect:/admin/admin-orderManage.do";
	}

	@GetMapping("/admin/admin-orderManage.do")
	public String adminOrderManage(@RequestParam(value="currentPage", defaultValue="1") int currentPage,  
			Model model) {
		log.info("admin-OrderManage");
		

		PageDTO pageDTO = new PageDTO();
		PageMaker pageMaker = new PageMaker();	
		
		pageDTO.setRecordsPerPage(8);
		int maxNum = orderManageService.getAllOrderRecordNum(); 
		int maxPage = (int)(maxNum / pageDTO.getRecordsPerPage() + 0.95) + 1;
		pageDTO.setMaxPage(maxPage);
		pageDTO.setCurrentPage(currentPage  < pageDTO.getMaxPage() ? currentPage : pageDTO.getMaxPage());
		
		pageMaker.setPageDTO(pageDTO);
					
		List<Map<String, Object>> ordermanageList = orderManageService.getAllOrderByPaging(pageDTO.getCurrentPage(), pageDTO.getRecordsPerPage());
		List<OrderVO> orderList = toOrderList(ordermanageList);		
		
		model.addAttribute("orderManageList", orderList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/admin/admin-orderManage";
	}
	
	private List<OrderVO> toOrderList(List<Map<String, Object>> list) {
		
		log.info("변환");

		List<OrderVO> resultList = new ArrayList<>();
		OrderVO orderVO;
		
		for (Map<String, Object> map : list) {
			
			orderVO = new OrderVO(map);
			resultList.add(orderVO);
		}
		
		return resultList;
	}
}
