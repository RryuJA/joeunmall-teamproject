package com.joeun.joeunmall.vo;

import java.sql.Date;

public class OrderVO {

		/** 주문번호 */
		private String orderIndex;
		/** 고객번호 */
		private String userIndex;
		/** 진행상태번호 */
		private	String orderStateIndex;
		/** 결제유형번호 */
		private String paymentIndex;
		/** 주문일자 */
		private Date orderDate;
		/** 총액 */
		private int orderPrice;
		/** 수령인 */
		private String orderName;
		/** 우편번호 */
		private String orderPost;
		/** 주소 */
		private String orderAddress;
		/** 상세주소 */
		private String orderAddressDetail;
		/** 연락처 */
		private String orderMobile;
		/** 배송메시지 */
		private String orderMessage;
		/** 상품명 */
		private String productName;
		/** 총 주문수량 */
		private int productCount;

//		/** 진행상태 */
//		private String orderStateIndex;

		
		public OrderVO(String orderIndex, String orderName, String productName, int productCount, int orderPrice,
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



