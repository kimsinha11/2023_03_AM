package com.KoreaIT.java.AM.dto;

public class Member extends Dto {
	public String name;
	public String loginId;
	public String loginPw;

	public Member(int id, String name, String loginId, String loginPw, String regDate, String updateDate) {
		this.id = id;
		this.name = name;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.regDate = regDate;
		this.updateDate = updateDate;

	}

}