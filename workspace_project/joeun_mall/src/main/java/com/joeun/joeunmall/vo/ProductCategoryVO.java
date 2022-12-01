package com.joeun.joeunmall.vo;

public class ProductCategoryVO {
	/** 카테고리번호 */
	private String productCategoryIndex;
	/** 카테고리명 */
	private String productCategoryName;
	
	public String getProductCategoryIndex() {
		return productCategoryIndex;
	}
	public void setProductCategoryIndex(String productCategoryIndex) {
		this.productCategoryIndex = productCategoryIndex;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	
	@Override
	public String toString() {
		return "ProductCategoryVO [productCategoryIndex=" + productCategoryIndex + ", productCategoryName="
				+ productCategoryName + "]";
	}
	
}
