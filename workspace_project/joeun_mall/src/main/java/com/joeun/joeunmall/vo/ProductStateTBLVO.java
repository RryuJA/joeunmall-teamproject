package com.joeun.joeunmall.vo;

import java.io.Serializable;

/**
 * 판매 상태 정보
 * 
 * @author PJM
 */

public class ProductStateTBLVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 판매 상태 번호 */
	private int productStateIndex;
	
	/** 판매 상태 정보 */
	private String productStateInfo;
	
	public ProductStateTBLVO(int productStateIndex, String productStateInfo) {
		this.productStateIndex = productStateIndex;
		this.productStateInfo = productStateInfo;
	}

	public int getProductStateIndex() {
		return productStateIndex;
	}

	public void setProductStateIndex(int productStateIndex) {
		this.productStateIndex = productStateIndex;
	}

	public String getProductStateInfo() {
		return productStateInfo;
	}

	public void setProductStateInfo(String productStateInfo) {
		this.productStateInfo = productStateInfo;
	}

	@Override
	public String toString() {
		return "ProductStateTBLVO [productStateIndex=" + productStateIndex + ", productStateInfo=" + productStateInfo
				+ "]";
	}
	
	
}