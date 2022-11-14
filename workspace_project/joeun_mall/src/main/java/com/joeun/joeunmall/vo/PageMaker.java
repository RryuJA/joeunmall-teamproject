package com.joeun.joeunmall.vo;

public class PageMaker {
    
    private PageDTO pageDTO;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 5;
    
    public PageDTO getPageDTO() {
        return pageDTO;
    }
    public void setPageDTO(PageDTO pageDTO) {
        this.pageDTO = pageDTO;
        calcData();
    }
    
    private void calcData() {
        
        endPage = (int) (Math.ceil(pageDTO.getCurrentPage() / (double) displayPageNum) * displayPageNum);
 
        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;
        
        int tempEndPage = (int) (Math.ceil(pageDTO.getMaxPage() / (double) pageDTO.getMaxPage()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
 
        prev = startPage == 1 ? false : true;
        next = endPage * pageDTO.getRecordsPerPage() < pageDTO.getMaxPage() ? true : false;
        
    }
    
    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public int getdisplayPageNum() {
        return displayPageNum;
    }
    public void setdisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }
	@Override
	public String toString() {
		return "PageMaker [pageDTO=" + pageDTO + ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev
				+ ", next=" + next + ", displayPageNum=" + displayPageNum + "]";
	}
 
}