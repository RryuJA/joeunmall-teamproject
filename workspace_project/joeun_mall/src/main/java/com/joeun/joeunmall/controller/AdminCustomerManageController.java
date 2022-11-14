package com.joeun.joeunmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.joeunmall.service.CustomerManageService;
import com.joeun.joeunmall.vo.PageDTO;
import com.joeun.joeunmall.vo.PageMaker;
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
	
	@Autowired
	CustomerManageService customerManageService;

	@GetMapping("/customerManage.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "customerManage");
		return "redirect:/admin/admin-customerManage.do";
	}
	
	@GetMapping("/admin/admin-customerManage.do")
	public String adminCustomerManage(@RequestParam(value="currentPage", defaultValue="1") int currentPage,  
			Model model) {
		log.info("admin-customerManage");
		
		PageDTO pageDTO = new PageDTO();
		PageMaker pageMaker = new PageMaker();
		
				
		pageDTO.setRecordsPerPage(8);
		int maxNum = customerManageService.getAllRecordNum(); 
		int maxPage = (int)(maxNum / pageDTO.getRecordsPerPage() + 0.95) + 1;
		pageDTO.setMaxPage(maxPage);
		pageDTO.setCurrentPage(currentPage  < pageDTO.getMaxPage() ? currentPage : pageDTO.getMaxPage());
		
		pageMaker.setPageDTO(pageDTO);
	
		
		
		//List<CustomerManageVO> customermanageList = customerManageService.getAllUserByPaging(currentPage, pageDTO.getRecordsPerPage());
		List<UserVO> customermanageList = customerManageService.getAllUserByPaging(pageDTO.getCurrentPage(), pageDTO.getRecordsPerPage());
		
		model.addAttribute("customerManageList", customermanageList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/admin/admin-customerManage";
	}
	
}
