package com.joeun.joeunmall.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CartVO {

	/** 장바구니번호 */
	private String cartIndex;
	/** 고객번호 */
	private String userIndex;
	/** 상품번호 */
	private String productIndex;
	/** 상품옵션 번호 */
	private String productOptionIndex;
	/** 상품개수 */
	private int productCount;
	/** 상품단가 */
	private int productPrice;
	/** 상품이름 */
	private String productName;
	
}
