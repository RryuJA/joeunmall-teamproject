package com.joeun.joeunmall_pjm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellPriceQuantityVO {
	
	/** 상품 id */
	private String id;
	
	/** 상품명 */
	private String name;
	
	/** 판매 수량 */
	private int quantity;
	
	/** 판매 금액 */
	private int price;
	
}
