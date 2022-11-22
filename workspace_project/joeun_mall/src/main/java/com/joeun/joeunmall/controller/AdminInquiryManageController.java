package com.joeun.joeunmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.joeunmall.service.InquiryManageService;
import com.joeun.joeunmall.vo.InquiryVO;
import com.joeun.joeunmall.vo.PageDTO;
import com.joeun.joeunmall.vo.PageMaker;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3 LSE
 *
 */
@Controller
@Slf4j
@RequestMapping
public class AdminInquiryManageController {

	@Autowired
	InquiryManageService inquiryManageService;
	
	@GetMapping("/inquiryManage.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "inquiryManage");
		return "redirect:/admin/admin-inquiryManage.do";
	}
	
	@GetMapping("/admin/admin-inquiryManage.do")
	public String adminInquiryManage(@RequestParam(value="currentPage", defaultValue="1") int currentPage, 
			Model model) {
		log.info("admin-inquiryManage");
		
		PageDTO pageDTO = new PageDTO();
		PageMaker pageMaker = new PageMaker();
		
		pageDTO.setRecordsPerPage(7);
		int maxNum = inquiryManageService.getAllInquiryRecordNum();
		int maxPage = (int)(maxNum / pageDTO.getRecordsPerPage() + 0.95) + 1;
		pageDTO.setMaxPage(maxPage);
		pageDTO.setCurrentPage(currentPage < pageDTO.getMaxPage() ? currentPage : pageDTO.getMaxPage());
		
		pageMaker.setPageDTO(pageDTO);
		
		List<InquiryVO> inquirymanageList = inquiryManageService.getAllInquiryByPaging(pageDTO.getCurrentPage(), pageDTO.getRecordsPerPage());
		
		model.addAttribute("inquiryManageList", inquirymanageList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/admin/admin-inquiryManage";
	}
}