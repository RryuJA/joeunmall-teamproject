package com.joeun.joeunmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.joeunmall.service.ProductService;
import com.joeun.joeunmall.vo.PageDTO;
import com.joeun.joeunmall.vo.PageMaker;
import com.joeun.joeunmall.vo.ProductImageVO;
import com.joeun.joeunmall.vo.ProductOptionVO;
import com.joeun.joeunmall.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class UserProductController {
	
	@Autowired ProductService productService;
		
	@GetMapping("/user/user-productDetail.do")
	public String userProductDetail(@RequestParam(value="productIndex") String productIndex, Model model) {
		log.info("userProductDetail");
		
		ProductVO productVO = productService.selectProductInfo(productIndex);
		List<ProductImageVO> productImageList = productService.selectProductImage(productIndex);
		List<ProductOptionVO> productOptionList = productService.selectProductOption(productIndex);
		
		model.addAttribute("productVO", productVO);
		model.addAttribute("productImageList", productImageList);
		model.addAttribute("productOptionList", productOptionList);
		
		return "/user/user-productDetail";
	}
	
	// ------------------------------------------------------------------	
	// 상품리스트
	@GetMapping("/user/user-productlistCarousel.do")
	public String userProductlistCarousel(@RequestParam(value="currentPage", defaultValue="1") int currentPage, 
			Model model) {
		log.info("userProductlistCarousel");
		
		//상품 갤러리 정보 확보 
		//페이지당 8개 상품 출력
		PageDTO pageDTO = new PageDTO();
		PageMaker pageMaker = new PageMaker();	
		//총 상품 수 
		pageDTO.setRecordsPerPage(8);
		int maxNum = productService.selectProductCount();
		int maxPage = (int)(maxNum / pageDTO.getRecordsPerPage() + 0.95) + 1;
		pageDTO.setMaxPage(maxPage);
		pageDTO.setCurrentPage(currentPage  < pageDTO.getMaxPage() ? currentPage : pageDTO.getMaxPage());
		
		pageMaker.setPageDTO(pageDTO);

		
		
		model.addAttribute("products", productService.selectProductsByPaging(currentPage, 8));
		model.addAttribute("pageMaker", pageMaker);
		
		return "/user/user-productlistCarousel";
	}
	
	
	@GetMapping("/user/user-productlistCarouselCategory.do")
	public String userProductlistCarouselCategory(@RequestParam(value="currentPage", defaultValue="1") int currentPage, 
			@RequestParam("productCategoryIndex") String productCategoryIndex,Model model) {
		log.info("userProductlistCarousel");
		
		//상품 갤러리 정보 확보 
		//페이지당 8개 상품 출력
		PageDTO pageDTO = new PageDTO();
		PageMaker pageMaker = new PageMaker();	
		//총 상품 수 
		pageDTO.setRecordsPerPage(8);
		int maxNum = productService.selectProductsCountByCategory(productCategoryIndex);
		int maxPage = (int)(maxNum / pageDTO.getRecordsPerPage() + 0.95) + 1;
		pageDTO.setMaxPage(maxPage);
		pageDTO.setCurrentPage(currentPage  < pageDTO.getMaxPage() ? currentPage : pageDTO.getMaxPage());
		
		pageMaker.setPageDTO(pageDTO);

		
		model.addAttribute("products", productService.selectProductsByPagingAndCategory(currentPage, 8, productCategoryIndex));
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("productCategoryIndex", productCategoryIndex);
		
		return "/user/user-productlistCarousel";
	}

}
