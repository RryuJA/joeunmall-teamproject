package com.joeun.joeunmall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraphDataVO {
	
	/** 상품명 */
	private String pn;
	
	/** 판매 금액 */
	private int price;
	
	/** 판매 수량*/
	private int amount;
	
	/** 판매 기간 */
	private String period;
	
	/** 상품 ct(의류 종류) */
	private String ct;
}
