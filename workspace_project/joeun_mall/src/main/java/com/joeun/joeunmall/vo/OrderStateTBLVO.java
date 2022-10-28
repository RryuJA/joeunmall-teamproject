package com.joeun.joeunmall.vo;

import java.io.Serializable;

/** 
 * 주문 진행상태 
 * @author PJM
 */

public class OrderStateTBLVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 진행 상태 번호 */
	private String orderStateIndex;
	
	/** 진행 상태 정보 */
	private String orderStateInfo;
	
	public OrderStateTBLVO() {
		
	}
	
	public OrderStateTBLVO(String orderStateIndex, String orderStateInfo) {
		this.orderStateIndex = orderStateIndex;
		this.orderStateInfo = orderStateInfo;
	}

	public String getOrderStateIndex() {
		return orderStateIndex;
	}

	public void setOrderStateIndex(String orderStateIndex) {
		this.orderStateIndex = orderStateIndex;
	}

	public String getOrderStateInfo() {
		return orderStateInfo;
	}

	public void setOrderStateInfo(String orderStateInfo) {
		this.orderStateInfo = orderStateInfo;
	}

	@Override
	public String toString() {
		return "OrderStateTBOVL [orderStateIndex=" + orderStateIndex + ", orderStateInfo=" + orderStateInfo + "]";
	}
	
	
}