package com.joeun.joeunmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.joeunmall.service.ProductManageService;
import com.joeun.joeunmall.vo.PageDTO;
import com.joeun.joeunmall.vo.PageMaker;
import com.joeun.joeunmall.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3
 *
 */
@Controller
@Slf4j
@RequestMapping
public class AdminProductManageController {

	@Autowired
	ProductManageService productManageService; 
	
	@GetMapping("/productManage.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "productManage");
		return "redirect:/admin/admin-productManage.do";
	}
		
	@GetMapping("/admin/admin-productManage.do")
	public String adminProductManage(@RequestParam(value="currentPage", defaultValue="1") int currentPage,  
			Model model) {
		log.info("admin-productManage");
		
		PageDTO pageDTO = new PageDTO();
		PageMaker pageMaker = new PageMaker();	
		
		pageDTO.setRecordsPerPage(6);
		int maxNum = productManageService.getAllProductRecordNum(); 
		int maxPage = (int)(maxNum / pageDTO.getRecordsPerPage() + 0.95) + 1;
		pageDTO.setMaxPage(maxPage);
		pageDTO.setCurrentPage(currentPage  < pageDTO.getMaxPage() ? currentPage : pageDTO.getMaxPage());
		
		pageMaker.setPageDTO(pageDTO);
					
		List<ProductVO> productmanageList = productManageService.getAllProductByPaging(pageDTO.getCurrentPage(), pageDTO.getRecordsPerPage());
				
		model.addAttribute("productManageList", productmanageList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/admin/admin-productManage";
	}
}
