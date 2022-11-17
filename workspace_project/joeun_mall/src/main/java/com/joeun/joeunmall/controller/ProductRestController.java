package com.joeun.joeunmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joeun.joeunmall.service.ProductRegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductRestController {

	@Autowired
	ProductRegistrationService productService;
	
	@GetMapping(value = "/admin/admin-productIndex.do", produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> getMaxProductIndex(@RequestParam("yearCate") String yearCate){
	log.info("/admin/admin-productIndex.do/");
		
		String maxProductIndex = productService.selectMaxProductIndex(yearCate);
		log.info("maxProductIndex=" + maxProductIndex);
		
		return new ResponseEntity<>(maxProductIndex, HttpStatus.OK);
	}
}
