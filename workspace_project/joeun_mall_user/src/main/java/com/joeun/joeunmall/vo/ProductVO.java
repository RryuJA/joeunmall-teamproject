package com.joeun.joeunmall.vo;

import java.util.Date;

/** 
 * 상품정보 VO(값 객체) 
 * 
 * @author team3
 */
public class ProductVO {
	
	/** 상품번호 */
	private String productIndex;

	/** 카테고리번호 */
	private String productCategoryIndex;
	
	/** 상품명 */
	private String productName;
	
	/** 판매상태번호 */
	private String productStateIndex;
	
	/** 상품이미지 */
	private String productImage;

	/** 상품가격 */
	private int productPrice;

	/** 등록일자 */
	private Date productDate;
	
	/** 상품 상세정보 */
	private String productInfo;
	
	/** 상품옵션 */
	private String productOptionValue; 

	public String getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
	}

	public String getProductCategoryIndex() {
		return productCategoryIndex;
	}

	public void setProductCategoryIndex(String productCategoryIndex) {
		this.productCategoryIndex = productCategoryIndex;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductStateIndex() {
		return productStateIndex;
	}

	public void setProductStateIndex(String productStateIndex) {
		this.productStateIndex = productStateIndex;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getProductOptionValue() {
		return productOptionValue;
	}

	public void setProductOptionValue(String productOptionValue) {
		this.productOptionValue = productOptionValue;
	}

	@Override
	public String toString() {
		return "ProductVO [productIndex=" + productIndex + ", productCategoryIndex=" + productCategoryIndex
				+ ", productName=" + productName + ", productStateIndex=" + productStateIndex + ", productImage="
				+ productImage + ", productPrice=" + productPrice + ", productDate=" + productDate + ", productInfo="
				+ productInfo + ", productOptionValue=" + productOptionValue + "]";
	}
	
}
