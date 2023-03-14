package com.KoreaIT.java.AM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	public int memberId;

	public Article(int id, int memberId, String title, String body, String regDate, String updateDate) {
		this(id, memberId, title, body, regDate, updateDate, 0);

	}

	public Article(int id, int memberId, String title, String body, String regDate, String updateDate, int hit) {
		this.id = id;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.hit = hit;
	}

}