package com.douzone.mysite.vo;

public class BoardVo {
	private long no;
	private String title;
	private String contents;
	private long hit;
	private String regDate;
	private Integer gNo;
	private long oNo;
	private long depth;
	private long userNo;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", hit=" + hit + ", regDate="
				+ regDate + ", gNo=" + gNo + ", oNo=" + oNo + ", depth=" + depth + ", userNo=" + userNo + ", userName="
				+ userName + "]";
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String redDate) {
		this.regDate = redDate;
	}
	public Integer getgNo() {
		return gNo;
	}
	public void setgNo(Integer gNo) {
		this.gNo = gNo;
	}
	public long getoNo() {
		return oNo;
	}
	public void setoNo(long oNo) {
		this.oNo = oNo;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}


	
}
