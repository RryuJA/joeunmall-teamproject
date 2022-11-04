package demo;


public class GraphDataVO {
	
	/** 상품명 */
	private String pn;
	
	/** 판매 금액 */
	private int price;
	
	/** 판매 기간 */
	private String period;
	
	/** 상품 ct(의류 종류) */
	private String ct;

	public GraphDataVO(){
		
	}

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public GraphDataVO(String pn, int price, String period, String ct) {
		super();
		this.pn = pn;
		this.price = price;
		this.period = period;
		this.ct = ct;
	}

	@Override
	public String toString() {
		return "GraphDataVO [pn=" + pn + ", price=" + price + ", period=" + period + ", ct=" + ct + "]";
	};
	
	
	
}	