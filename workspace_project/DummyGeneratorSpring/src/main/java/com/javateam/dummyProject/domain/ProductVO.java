package com.javateam.dummyProject.domain;

import java.io.Serializable;
import java.util.Date;

/** 
 * 상품정보 VO(값 객체) 
 * 
 * @author team3
 */
public class ProductVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 상품번호 : '상품등록연도 2자리'_'상품 카테고리 번호 2자리'_'상품 등록 순서 3자리' ex) 00_00_000 */
	private String productIndex;

	/** 주문번호 : '주문일자 6자리'_'주문일자의 주문순서 3자리' ex) 000000_000 */
	private String orderIndex;
	
	/** 주문자명  */
	private String userName;

	/** 상품명 */
	private String productName;
	
	/** 상품옵션  */
	private String productOption; 
	
	/** 상품가격 */
	private int productPrice;
	
	/** 등록일자 */
	private Date productDate;

	public ProductVO() {
	
	}
		
	public ProductVO(String productIndex, String orderIndex, String userName, String productName, String productOption,
			int productPrice, Date productDate) {
		this.productIndex = productIndex;
		this.orderIndex = orderIndex;
		this.userName = userName;
		this.productName = productName;
		this.productOption = productOption;
		this.productPrice = productPrice;
		this.productDate = productDate;
	}

	public String getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
	}

	public String getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductOption() {
		return productOption;
	}

	public void setProductOption(String productOption) {
		this.productOption = productOption;
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

	@Override
	public String toString() {
		return "ProductVO [productIndex=" + productIndex + ", orderIndex=" + orderIndex + ", userName=" + userName
				+ ", productName=" + productName + ", productOption=" + productOption + ", productPrice=" + productPrice
				+ ", productDate=" + productDate + "]";
	}
	
}
