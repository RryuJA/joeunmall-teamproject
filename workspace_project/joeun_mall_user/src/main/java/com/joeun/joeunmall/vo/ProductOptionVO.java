package com.joeun.joeunmall.vo;

public class ProductOptionVO {
	
	/** 
	 * 상품옵션정보 VO(값 객체) 
	 * 
	 * @author team3
	 */

	/** 상품옵션번호 */
	private String productOptionIndex;
	/** 상품번호 */
	private String productIndex;
	/** 상품옵션 */
	private String productOptionValue;
	
	public ProductOptionVO(String productOptionIndex, String productIndex, String productOptionValue) {

		this.productOptionIndex = productOptionIndex;
		this.productIndex = productIndex;
		this.productOptionValue = productOptionValue;
	}
	
	public String getProductOptionIndex() {
		return productOptionIndex;
	}
	public void setProductOptionIndex(String productOptionIndex) {
		this.productOptionIndex = productOptionIndex;
	}
	public String getProductIndex() {
		return productIndex;
	}
	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
	}
	public String getProductOptionValue() {
		return productOptionValue;
	}
	public void setProductOptionValue(String productOptionValue) {
		this.productOptionValue = productOptionValue;
	}

	@Override
	public String toString() {
		return "ProductOptionVO [productOptionIndex=" + productOptionIndex + ", productIndex=" + productIndex
				+ ", productOptionValue=" + productOptionValue + "]";
	}
		
}
