package com.joeun.joeunmall.vo;

import java.util.Date;

/**
 * OrderManage 주문관리정보 VO(값 객체)
 * 
 * @author Team3
 * */
public class OrderManageVO {
	/** 주문번호 */
	private String orderIndex;
	
	/** 주문자명 */
	private String orderName;
	
	/** 상품명 */
	private String productName;
	
	/** 총 주문수량 */
	private int productCount;
	
	/** 총 상품가격 */
	private int orderPrice;
	
	/** 주문일자 */
	private Date orderDate;
	
//	/** 진행상태 */
//	private String orderStateIndex;

	public OrderManageVO(String orderIndex, String orderName, String productName, int productCount, int orderPrice,
			Date orderDate) {
		this.orderIndex = orderIndex;
		this.orderName = orderName;
		this.productName = productName;
		this.productCount = productCount;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
	}

	public String getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "OrderManageVO [orderIndex=" + orderIndex + ", orderName=" + orderName + ", productName=" + productName
				+ ", productCount=" + productCount + ", orderPrice=" + orderPrice + ", orderDate=" + orderDate + "]";
	}
}
