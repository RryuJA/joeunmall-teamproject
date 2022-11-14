package com.joeun.joeunmall.vo;

public class PageDTO {
	
	/** 현재 페이지 */
	private int currentPage;
	
	/** 페이지 당 레코드 수 */
	private int recordsPerPage;
	
	/** 총 페이지 수 */
	private int maxPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	@Override
	public String toString() {
		return "PageDTO [currentPage=" + currentPage + ", recordsPerPage=" + recordsPerPage + ", maxPage=" + maxPage
				+ "]";
	}
}
