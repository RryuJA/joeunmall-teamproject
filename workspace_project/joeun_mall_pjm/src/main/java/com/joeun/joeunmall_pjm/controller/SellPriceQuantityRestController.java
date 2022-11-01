package com.joeun.joeunmall_pjm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SellPriceQuantityRestController {
	
	/**
	 * ex) http://localhost:8282/joeunmall_pjm/graphJson/priceQuantity?selectGraph=price
	 * @param selectGraph
	 * @return
	 */
	@GetMapping(value = "/graphJson/priceQuantity", produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> getGraphJson(@RequestParam("selectGraph") String selectGraph) {
		log.info("getGraphJson");
		log.info("selectGraph: " + selectGraph);
		
		String result = selectGraph.equals("price") ? "가격" : "수량";
		return new ResponseEntity<> (result, HttpStatus.OK);
	}
	
	
}
