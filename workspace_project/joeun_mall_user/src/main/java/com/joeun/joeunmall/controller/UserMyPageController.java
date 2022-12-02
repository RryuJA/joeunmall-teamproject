package com.joeun.joeunmall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.joeunmall.service.UserMypageInquiryService;
import com.joeun.joeunmall.service.UserService;
import com.joeun.joeunmall.vo.InquiryVO;
import com.joeun.joeunmall.vo.OrderVO;
import com.joeun.joeunmall.vo.PageDTO;
import com.joeun.joeunmall.vo.PageMaker;
import com.joeun.joeunmall.vo.UserVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class UserMyPageController {
	
	@Autowired UserService userService;
	
	@Autowired
	UserMypageInquiryService userMypageInquiryService;
	
	//유저-마이페이지-주문내역
	@GetMapping("/user/user-mypageOrder.do") 
	public String userProductManage(@RequestParam(value="currentPage", defaultValue="1") int currentPage,  
			 Model model, HttpSession session) {
		log.info("마이페이지 로그:");
		String userId = session.getAttribute("SESS_LOGIN_ID").toString();
		UserVO userVO = userService.selectUser(userId);
		
		PageDTO pageDTO = new PageDTO();
		PageMaker pageMaker = new PageMaker();	
		
		pageDTO.setRecordsPerPage(5);
		int maxNum = userService.selectMypageUserIndexNum(userVO.getUserIndex()); 
		int maxPage = (int)(maxNum / pageDTO.getRecordsPerPage() + 0.95) + 1;
		pageDTO.setMaxPage(maxPage);
		pageDTO.setCurrentPage(currentPage  < pageDTO.getMaxPage() ? currentPage : pageDTO.getMaxPage());
		
		pageMaker.setPageDTO(pageDTO);

		List<OrderVO> userMypageOrderList = userService.selectMypageUserIndex(pageDTO.getCurrentPage(), pageDTO.getRecordsPerPage(), userVO.getUserIndex());
		List<OrderVO> orderList = toOrderList(userMypageOrderList);		
		
		model.addAttribute("userMypageOrderList", orderList);
		model.addAttribute("pageMaker", pageMaker);

		return "/user/user-mypageOrder";
	}
	
	// 유저-마이페이지-주문관리 페이지 상품명 (파란색티셔츠 외 N개) 표시컨트롤
	private List<OrderVO> toOrderList(List<OrderVO> list) {
		
		log.info("변환");

		List<OrderVO> resultList = new ArrayList<>();
		
		for (OrderVO orderVO : list) {

			//주문번호에 포함되는 모든 제품명을 뽑는 쿼리
			List<String> productList = userService.getOrderProductsName(orderVO.getOrderIndex());
			if (productList.size() > 0) {
				orderVO.setProductNames(productList);
			}
			resultList.add(orderVO);
		}
		
		return resultList;
	}
	
	//유저-마이페이지-내문의글
	@GetMapping("/user/user-mypageInquiry.do")
	public String userMypageInquiry(@RequestParam(value="currentPage", defaultValue="1") int currentPage,  
			Model model, HttpSession session) {
		log.info("user-MypageInquiry");
		String userId = session.getAttribute("SESS_LOGIN_ID").toString();
		UserVO userVO = userService.selectUser(userId);
		
		PageDTO pageDTO = new PageDTO();
		PageMaker pageMaker = new PageMaker();
						
		pageDTO.setRecordsPerPage(5);
		int maxNum = userMypageInquiryService.selectMyPageInquiryIndexNum(userVO.getUserIndex()); 
		int maxPage = (int)(maxNum / pageDTO.getRecordsPerPage() + 0.95) + 1;
		pageDTO.setMaxPage(maxPage);
		pageDTO.setCurrentPage(currentPage  < pageDTO.getMaxPage() ? currentPage : pageDTO.getMaxPage());
		
		pageMaker.setPageDTO(pageDTO);
	
		List<InquiryVO> userMypageInquiryList = userMypageInquiryService.selectMyPageInquiryIndex(pageDTO.getCurrentPage(), pageDTO.getRecordsPerPage(), userVO.getUserIndex());
		
		model.addAttribute("userMypageInquiryList", userMypageInquiryList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/user/user-mypageInquiry";
	}
}
