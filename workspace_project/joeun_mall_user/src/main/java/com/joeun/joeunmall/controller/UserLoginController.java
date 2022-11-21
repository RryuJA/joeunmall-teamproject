package com.joeun.joeunmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class UserLoginController {
	
	@GetMapping("/")
	public String demo(Model model) {
		log.info("demo");
		return  "redirect:/user/user-login.do";
	}
	
	
	@GetMapping("/user/user-login.do")
	public String userLogin() {
		log.info("userLogindo");
		return "/user/user-login";
	}
	
	
	
	
	
	@GetMapping("/user/user-mypageOrder.do")
	public String mypageORder() {
		log.info("mypageORderdo");
		return "/user/user-mypageOrder";
	}
	
	@GetMapping("/user/user-productDetail.do")
	public String userProductDetail() {
		log.info("userProductDetaildo");
		return "/user/user-productDetail";
	}
	
	@GetMapping("/user/user-productlistAll.do")
	public String userProductlistAll() {
		log.info("userProductDetaildo");
		return "/user/user-productlistAll";
	}
	
	@GetMapping("/user/user-shoppingBasket.do")
	public String userShoppingBasket() {
		log.info("userShoppingBasketdo");
		return "/user/user-shoppingBasket";
	}

}
