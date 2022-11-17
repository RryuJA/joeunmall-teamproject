package com.joeun.joeunmall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatsVO {

	/**No*/
	private String orderIndex;
	/**날짜*/
	private String sellPeriod;
	/**상품명*/
	private String productName;
	/**카테고리선택*/
	private String clothType;
	/**판매수량*/
	private int amount;
	/**개별 가격*/
	private int price;
	/**총 판매금액*/
	private int total;
	
}
