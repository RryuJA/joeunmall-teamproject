package com.javateam.dummyProject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.dummyProject.service.DummyRealTargetSiteServiceUpgrade;
import com.javateam.dummyProject.service.DummyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	DummyRealTargetSiteServiceUpgrade drtsuSvc;
	
	@GetMapping("/")
	public String home(@RequestParam(value="cate", defaultValue="01") String cate,
					   @RequestParam(value="subCate", defaultValue="001") String subCate,
					   Model model) throws ClassNotFoundException, IOException {
		
		log.info("home : cate => 01 ~ 05");
		
		// 직렬화
		// dummySvc.crawlProducts(); 
		
		// 역직렬화 : ex) ser/PerfectPrList_01_001.ser => List<ProductVO>
		model.addAttribute("prList", 
				drtsuSvc.getProductsFromSerFile("ser/PerfectPrList_"+ cate + "_" + subCate + ".ser"));
		
		return "home"; 
	}

}
