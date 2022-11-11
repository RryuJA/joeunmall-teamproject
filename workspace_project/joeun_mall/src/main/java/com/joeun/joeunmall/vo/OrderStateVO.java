package com.joeun.joeunmall.vo;

import java.util.Date;

public class OrderStateVO {

	/** 진행상태번호 */
	private String orderStateIndex;
	/** 진행상태정보 */
	private String orderStateInfo;
	/**No*/
	private int statNumber;
	/**날짜*/
	private Date statDate;
	/**상품명*/
	private String statName;
	/**카테고리선택*/
	private String statCategory;
	/**판매수량*/
	private int statSale;
	/**개별 가격*/
	private int statPrice;
	/**총 판매금액*/
	private int statTotal;
	/**No*/
	
public OrderStateVO() {
	}
	public OrderStateVO(int statNumber, Date statDate, String statName, String statCategory, int statSale,
			int statPrice, int statTotal) {
		this.statNumber = statNumber;
		this.statDate = statDate;
		this.statName = statName;
		this.statCategory = statCategory;
		this.statSale = statSale;
		this.statPrice = statPrice;
		this.statTotal = statTotal;
	}
 
	public int getStatNumber() {
		return statNumber;
	}

 
	public void setStatNumber(int statNumber) {
		this.statNumber = statNumber;
	}

	public Date getStatDate() {
		return statDate;
	}


	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

 
	public String getStatName() {
		return statName;
	}

 
	public void setStatName(String statName) {
		this.statName = statName;
	}

 

	public String getStatCategory() {
		return statCategory;
	}


	public void setStatCategory(String statCategory) {
		this.statCategory = statCategory;
	}

 
	public int getStatSale() {
		return statSale;
	}

 

	public void setStatSale(int statSale) {
		this.statSale = statSale;
	}

 
	public int getStatPrice() {
		return statPrice;
	}

 
	public void setStatPrice(int statPrice) {
		this.statPrice = statPrice;
	}


	public int getStatTotal() {
		return statTotal;
	}


	public void setStatTotal(int statTotal) {
		this.statTotal = statTotal;
	}

 
	@Override
	public String toString() {
		return "OrderstatsVO [statNumber=" + statNumber + ", statDate=" + statDate + ", statName=" + statName
				+ ", statCategory=" + statCategory + ", statSale=" + statSale + ", statPrice=" + statPrice
				+ ", statTotal=" + statTotal + "]";
	}
}
