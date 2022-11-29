package com.joeun.joeunmall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.joeunmall.service.UserService;
import com.joeun.joeunmall.vo.OrderVO;
import com.joeun.joeunmall.vo.PageDTO;
import com.joeun.joeunmall.vo.PageMaker;
import com.joeun.joeunmall.vo.UserVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class UserOrderController {
	
	@Autowired UserService userService;
	
	//수정 전 컨트롤러
/*	@GetMapping("/user/user-mypageOrdertemp.do")
	public String mypageORder(HttpSession session) {
		log.info("userMypageOrder");
		String userId = session.getAttribute("SESS_LOGIN_ID").toString();
		UserVO userVO = userService.selectUser(userId);
		session.setAttribute("userData", userVO); //유저 데이터
		return "/user/user-mypageOrder";
	}*/
	
	// 유저-마이페이지-주문관리 페이지 컨트롤 
	// (구버전)@ModelAttribute UserVO userVO,
	
	@GetMapping("/user/user-mypageOrder.do") 
	public String adminProductManage(@RequestParam(value="currentPage", defaultValue="1") int currentPage,  
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
	/*
	@GetMapping("/user/user-mypageModify.do")
	public String userMypageModify(HttpSession session) {
		log.info("userMypageModify");
		String userId = session.getAttribute("SESS_LOGIN_ID").toString();
		UserVO userVO = userService.selectUser(userId);
		session.setAttribute("defaultUser", userVO); //기존 정보
		return "/user/user-mypageModify";
	}
	
	@PostMapping("/user/user-mypageModifyProc.do")
	public String userMypageModifyProc(@ModelAttribute UserVO userVO, Model model,HttpSession session) {
		log.info("userMypageModifyProc");
		String msg = ""; //메시지
		String movePath = ""; //이동경로
		
		//패스워드 암호화
		BCryptPasswordEncoder bce = new BCryptPasswordEncoder();
		userVO.setUserPw(bce.encode(userVO.getUserPw()));
		
		log.info("userVO(update) ="+ userVO);
		
		//회원정보 수정
		if (userService.updateUser(userVO) == true) {
			msg = "회원정보 수정에 성공하셨습니다."; 
			movePath = "/user/user-mypageModify.do";
			session.removeAttribute("defaultUser");//기존 정보 세션 삭제
		} else {
			msg = "회원정보 수정에 실패하였습니다."; 
			movePath = "/user/user-mypageModify.do";
		}
		
		model.addAttribute("errMsg", msg);
		model.addAttribute("movePath", movePath);
		
		return "/error/error";
	}
	*/
}
