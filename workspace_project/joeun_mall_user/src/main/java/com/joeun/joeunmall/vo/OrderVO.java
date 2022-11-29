package com.joeun.joeunmall.vo;

import java.lang.reflect.Field;
// import java.sql.Date;
import java.math.BigDecimal;
import java.util.Date; // 수정 : map => VO 변환시 문제 해결 : javateacher 
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

// 관리자페이지의 orderVO와 같음
@Slf4j
public class OrderVO {
	
	/** 주문번호 */
	private String orderIndex;

	/** 고객번호 */
	private String userIndex;

	/** 진행상태번호 */
	private String orderStateIndex;

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
	
	/** 주문번호에 포함되는 모든 상품명 */
	private List<String> productNames;


	// 추가 : javateacher
	public OrderVO() {}
	
	public OrderVO(String orderIndex, String userIndex, String orderStateIndex, String paymentIndex, Date orderDate,
			int orderPrice, String orderName, String orderPost, String orderAddress, String orderAddressDetail,
			String orderMobile, String orderMessage, String productName, int productCountSum) {

		this.orderIndex = orderIndex;
		this.userIndex = userIndex;
		this.orderStateIndex = orderStateIndex;
		this.paymentIndex = paymentIndex;
		this.orderDate = orderDate;
		this.orderPrice = orderPrice;
		this.orderName = orderName;
		this.orderPost = orderPost;
		this.orderAddress = orderAddress;
		this.orderAddressDetail = orderAddressDetail;
		this.orderMobile = orderMobile;
		this.orderMessage = orderMessage;
		this.productName = productName;
		this.productCountSum = productCountSum;
	}

	// 추가 : javateacher
	public OrderVO(Map<String, Object> map) {

		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		Field field; // reflection 정보 활용
		
		while (it.hasNext()) {
			
			String fldName = it.next();
			
			log.info("필드명 : " + fldName);
			
		    try {
		    		// VO와 1:1 대응되는 필드들 처리 
			    	try {
			    			// ex) "USER_INDEX" => "userIndex" 치환
			    			// String camelCaseFldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, fldName);
			    			String camelCaseFldName = this.toLowerCamelCaseStr(fldName);
							field = this.getClass().getDeclaredField(camelCaseFldName);
							
							log.info(fldName + " : " + field.getName());
							field.setAccessible(true);
							
							Object value = map.get(fldName);
							
							// 멤버 필드에 "값" 입력
							try {
								
								String valType = value.getClass().getName();
								log.info("형변환 타입 : " + valType); 
								
								if (valType.equals("java.math.BigDecimal")== true) {
									
									field.set(this, ((BigDecimal)value).intValue());
									
								} else {
								
									field.set(this, value);
								} //	
								
							} catch (IllegalAccessException e) {
								log.error("형변환 문제"); 
								// e.printStackTrace();
							}	
							
					} catch (NoSuchFieldException e) {
						
						// 만약 VO와 1:1 대응되지 않는 인자일 경우는 이 부분에서 입력처리합니다.
						log.error("인자와 필드가 일치하지 않습니다."); 
						
					} // try
					
			} catch (SecurityException | IllegalArgumentException e) { 
				e.printStackTrace();
			} // try
		    
		} // while
	}
	
	/**
	 * snakecase 문자열을 lowerCamelcase 문자열로 변환 ex) 
	 *  
	 * @param str 변환할 snakecase 문자열
	 * @return 변환된 camelcase 문자열
	 */
	public String toLowerCamelCaseStr(String str) {
		
		log.info("snakecase to lowerCamelcase");
		
		String result = "";
		
		String strArr[] = str.split("_");
		
		for (String temp : strArr) {
			
			temp = temp.toLowerCase();
			temp = Character.toString(temp.charAt(0)).toUpperCase() + temp.substring(1);
			result += temp;
		} //
		
		result = Character.toString(result.charAt(0)).toLowerCase() + result.substring(1);
		
		return result;
	}

	public String getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductCountSum() {
		return productCountSum;
	}

	public void setProductCountSum(int productCountSum) {
		this.productCountSum = productCountSum;
	}
	
	public List<String> getProductNames() {
		return productNames;
	}

	public void setProductNames(List<String> productNames) {
		this.productNames = productNames;
	}

	@Override
	public String toString() {
		return "OrderVO [orderIndex=" + orderIndex + ", userIndex=" + userIndex + ", orderStateIndex=" + orderStateIndex
				+ ", paymentIndex=" + paymentIndex + ", orderDate=" + orderDate + ", orderPrice=" + orderPrice
				+ ", orderName=" + orderName + ", orderPost=" + orderPost + ", orderAddress=" + orderAddress
				+ ", orderAddressDetail=" + orderAddressDetail + ", orderMobile=" + orderMobile + ", orderMessage="
				+ orderMessage + ", productName=" + productName + ", productCountSum=" + productCountSum
				+ ", productNames=" + productNames.toString() + "]";
	}
}