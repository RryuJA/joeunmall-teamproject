package com.joeun.joeunmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joeun.joeunmall.service.OrderManageService;

import lombok.extern.slf4j.Slf4j;

/**
 * 주문관리 - 진행상태 - selectBox 수정기능
 * 진행상태 수정하면 DB에 반영
 * 
 * @author LSE
 *
 */
@RestController
@Slf4j
public class AdminOrderStateRestController {
	
	@Autowired
	OrderManageService orderManageService;

	@GetMapping(value="/admin/admin-orderStateUpdate.do", produces ="text/plain; charset=UTF-8")
	public String adminOrderStateUpdate(@RequestParam("orderIndex") String orderIndex, @RequestParam("orderStateIndex") String orderStateIndex) {
		log.info("admin-orderStateUpdate");
		log.info("orderIndex= " + orderIndex);
		log.info("orderStateIndex={} ", orderStateIndex ); 
		
		String msg=""; 	//에러메시지

		//주문상태 업데이트
		if (orderManageService.updateOrderState(orderIndex, orderStateIndex) == true) {
			msg = "주문상태 업데이트 완료";
		} else {
			msg = "주문상태 업데이트 실패";
		}
		
		return msg;
	}
}
