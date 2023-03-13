package com.KoreaIT.java.AM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;

	public Article(int id, String title, String body, String regDate, String updateDate) {
		this(id, title, body, regDate, updateDate, 0);

	}

	public Article(int id, String title, String body, String regDate, String updateDate, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.hit = hit;
	}


}