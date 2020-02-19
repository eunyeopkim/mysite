package com.douzone.mysite.vo;

public class Paging {

	
	private long blockStartNum;		//블락시작넘버
	private long blockLastNum;		//블락마지막넘버
	private long lastPageNum;		//마지막페이지
	private long curPage;			//현재페이지

	private long limit;				//갯수제한
	private long maxPage;
	private long startPage;
	private long endPage;
	
	private long count;
	public Paging() {
		
	}
	public Paging(long blockStartNum, long blockLastNum, long lastPageNum, long curPage, long limit, long maxPage, long startPage, long endPage, long count) {
		this.blockStartNum= blockStartNum;
		this.blockLastNum = blockLastNum;
		this.lastPageNum = lastPageNum;
		this.curPage = curPage;
		this.limit = limit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.count = count;
	}
	@Override
	public String toString() {
		return "Paging [blockStartNum=" + blockStartNum + ", blockLastNum=" + blockLastNum + ", lastPageNum="
				+ lastPageNum + ", curPage=" + curPage + ", limit=" + limit + ", maxPage=" + maxPage + ", startPage="
				+ startPage + ", endPage=" + endPage + ", count=" + count + "]";
	}

	public long getBlockStartNum() {
		return blockStartNum;
	}

	public void setBlockStartNum(long blockStartNum) {
		this.blockStartNum = blockStartNum;
	}

	public long getBlockLastNum() {
		return blockLastNum;
	}

	public void setBlockLastNum(long blockLastNum) {
		this.blockLastNum = blockLastNum;
	}

	public long getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(long lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public long getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(long maxPage) {
		this.maxPage = maxPage;
	}

	public long getStartPage() {
		return startPage;
	}

	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}

	public long getEndPage() {
		return endPage;
	}

	public void setEndPage(long endPage) {
		this.endPage = endPage;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}


	
}
