package com.joeun.joeunmall.vo;

public class ProductImageVO {
	
	/** 상품이미지번호 */
	private String productImageIndex;
	/** 상품번호 */
	private String productIndex;
	/** 상품이미지 */
	private String productDetailImage;
	
	public String getProductImageIndex() {
		return productImageIndex;
	}
	public void setProductImageIndex(String productImageIndex) {
		this.productImageIndex = productImageIndex;
	}
	public String getProductIndex() {
		return productIndex;
	}
	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
	}
	public String getProductDetailImage() {
		return productDetailImage;
	}
	public void setProductDetailImage(String productDetailImage) {
		this.productDetailImage = productDetailImage;
	}
	@Override
	public String toString() {
		return "ProductImageVO [productImageIndex=" + productImageIndex + ", productIndex=" + productIndex
				+ ", productDetailImage=" + productDetailImage + "]";
	}
	
}	
