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

import com.joeun.joeunmall.vo.InquiryManageVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3
 *
 */
@Controller
@Slf4j
@RequestMapping
public class InquiryManageController {

	@GetMapping("/inquiryManage.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "inquiryManage");
		return "redirect:/admin/admin-inquiryManage.do";
	}

	@GetMapping("/admin/admin-inquiryManage.do")
	public String adminInquiryManage(Model model) {
		log.info("admin-inquiryManage");
		
		List<InquiryManageVO> inquirymanageList = new ArrayList<>();
		InquiryManageVO inquirymanageVO;
		
		inquirymanageVO = new InquiryManageVO("221103001", Date.valueOf("2022-11-03"), "일승은", "교환", "티셔츠 사이즈가 맞지 않아서 교환하고싶어요.");
		inquirymanageList.add(inquirymanageVO);
		
		inquirymanageVO = new InquiryManageVO("221103002", Date.valueOf("2022-11-03"), "이승은", "교환", "티셔츠 사이즈가 맞지 않아서 교환하고싶어요.");
		inquirymanageList.add(inquirymanageVO);
		
		inquirymanageVO = new InquiryManageVO("221103003", Date.valueOf("2022-11-03"), "삼승은", "교환", "티셔츠 사이즈가 맞지 않아서 교환하고싶어요.");
		inquirymanageList.add(inquirymanageVO);
		
		inquirymanageVO = new InquiryManageVO("221103004", Date.valueOf("2022-11-03"), "사승은", "교환", "티셔츠 사이즈가 맞지 않아서 교환하고싶어요.");
		inquirymanageList.add(inquirymanageVO);
		
		inquirymanageVO = new InquiryManageVO("221103005", Date.valueOf("2022-11-03"), "오승은", "교환", "티셔츠 사이즈가 맞지 않아서 교환하고싶어요.");
		inquirymanageList.add(inquirymanageVO);
		
		inquirymanageVO = new InquiryManageVO("221103006", Date.valueOf("2022-11-03"), "육승은", "교환", "티셔츠 사이즈가 맞지 않아서 교환하고싶어요.");
		inquirymanageList.add(inquirymanageVO);
		
		inquirymanageVO = new InquiryManageVO("221103007", Date.valueOf("2022-11-03"), "칠승은", "교환", "티셔츠 사이즈가 맞지 않아서 교환하고싶어요.");
		inquirymanageList.add(inquirymanageVO);
		
		inquirymanageVO = new InquiryManageVO("221103008", Date.valueOf("2022-11-03"), "팔승은", "교환", "티셔츠 사이즈가 맞지 않아서 교환하고싶어요.");
		inquirymanageList.add(inquirymanageVO);
		
		model.addAttribute("inquiryManageList", inquirymanageList);
		
		return "/admin/admin-inquiryManage";
	}
}
