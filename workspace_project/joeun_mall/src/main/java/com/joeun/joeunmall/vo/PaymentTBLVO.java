package com.joeun.joeunmall.vo;

import java.io.Serializable;

/**
 * 결제유형 VO(값 객체)
 * 
 * @author PJM
 */ 

public class PaymentTBLVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 결제 유형 번호 */
	private String paymentIndex;
	
	/** 결제 방식 */
	private String paymentMethod;
	
	PaymentTBLVO(String paymentIndex, String paymentMethod){
		this.paymentIndex = paymentIndex;
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentIndex() {
		return paymentIndex;
	}

	public void setPaymentIndex(String paymentIndex) {
		this.paymentIndex = paymentIndex;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "PaymentTBLVO [paymentIndex=" + paymentIndex + ", paymentMethod=" + paymentMethod + "]";
	}
	
	
}