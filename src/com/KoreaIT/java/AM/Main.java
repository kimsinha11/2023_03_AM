package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;

public class Main {

	static List<Article> articles = new ArrayList<>();
	static List<Member> members = new ArrayList<>();

	public static void main(String[] args) {
		new App().start();

	}

}
