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
		
		/** 주문자명 */
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
		private int productCountSum;
		
		public OrderVO(String orderIndex, String orderName, String productName, int productCountSum, int orderPrice,
				Date orderDate, String orderStateIndex) {
			this.orderIndex = orderIndex;
			this.orderName = orderName;
			this.productName = productName;
			this.productCountSum = productCountSum;
			this.orderPrice = orderPrice;
			this.orderDate = orderDate;
			this.orderStateIndex = orderStateIndex;
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
	 
		public int getproductCountSum() {
			return productCountSum;
		}

		public void setproductCountSum(int productCountSum) {
			this.productCountSum = productCountSum;
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

	 

		public String getUserIndex() {
			return userIndex;
		}



		public void setUserIndex(String userIndex) {
			this.userIndex = userIndex;
		}



		public String getOrderStateIndex() {
			return orderStateIndex;
		}



		public void setOrderStateIndex(String orderStateIndex) {
			this.orderStateIndex = orderStateIndex;
		}



		public String getPaymentIndex() {
			return paymentIndex;
		}



		public void setPaymentIndex(String paymentIndex) {
			this.paymentIndex = paymentIndex;
		}



		public String getOrderPost() {
			return orderPost;
		}



		public void setOrderPost(String orderPost) {
			this.orderPost = orderPost;
		}



		public String getOrderAddress() {
			return orderAddress;
		}



		public void setOrderAddress(String orderAddress) {
			this.orderAddress = orderAddress;
		}



		public String getOrderAddressDetail() {
			return orderAddressDetail;
		}



		public void setOrderAddressDetail(String orderAddressDetail) {
			this.orderAddressDetail = orderAddressDetail;
		}



		public String getOrderMobile() {
			return orderMobile;
		}



		public void setOrderMobile(String orderMobile) {
			this.orderMobile = orderMobile;
		}



		public String getOrderMessage() {
			return orderMessage;
		}



		public void setOrderMessage(String orderMessage) {
			this.orderMessage = orderMessage;
		}



		@Override
		public String toString() {
			return "OrderVO [orderIndex=" + orderIndex + ", userIndex=" + userIndex + ", orderStateIndex="
					+ orderStateIndex + ", paymentIndex=" + paymentIndex + ", orderDate=" + orderDate + ", orderPrice="
					+ orderPrice + ", orderName=" + orderName + ", orderPost=" + orderPost + ", orderAddress="
					+ orderAddress + ", orderAddressDetail=" + orderAddressDetail + ", orderMobile=" + orderMobile
					+ ", orderMessage=" + orderMessage + ", productName=" + productName + ", productCountSum="
					+ productCountSum + "]";
		}



	}



