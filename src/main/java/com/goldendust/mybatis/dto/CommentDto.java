package com.goldendust.mybatis.dto;

public class CommentDto {
	private int cid;
	private int bnum;
	private String mid;
	private String ctext;
	private String cdate;

	public CommentDto() {
		super();
	}

	public CommentDto(int cid, int bnum, String mid, String ctext, String cdate) {
		super();
		this.cid = cid;
		this.bnum = bnum;
		this.mid = mid;
		this.ctext = ctext;
		this.cdate = cdate;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getCtext() {
		return ctext;
	}

	public void setCtext(String ctext) {
		this.ctext = ctext;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	
}