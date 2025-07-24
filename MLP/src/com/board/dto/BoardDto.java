package com.board.dto;

import java.util.Date;

public class BoardDto {
	private int no;
	private String name;
	private String title;
	private String content;
	private Date regdate;
	public BoardDto() {
	}
	public BoardDto(int no, String name, String title, String content, Date regdate) {
		super();
		this.no = no;
		this.name = name;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	

	
	
}
