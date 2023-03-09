package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static List<Article> articles = new ArrayList<>();

	static int lastArticleId = 0;
	static int count = 0;

	public static void main(String[] args) {
		makeTestData();
		Scanner sc = new Scanner(System.in);

		while (true) {
			String nowdate = Util.getNowDateStr();

			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
				} else {
					System.out.printf("번호    / 제목    / 날짜                  / 업데이트 날짜              / 조회수    \n");
				}
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%d    / %s    / %s    / %s    / %d    \n", article.id, article.title,
							article.regDate, article.updateDate, article.hit);
				}

			} else if (command.startsWith("article detail ")) {
				String[] detail = command.split(" ");

				int 번호 = Integer.parseInt(detail[2]);

				Article articlefound = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == 번호) {
						articlefound = article;
						break;
					}
				}

				if (articlefound == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", 번호);
					continue;

				} else {
					articlefound.hit++;
					System.out.printf("번호:%d\n제목:%s\n내용:%s\n날짜:%s\n업데이트날짜:%s\n조회수:%d\n", 번호, articlefound.title,
							articlefound.body, articlefound.regDate, articlefound.updateDate, articlefound.hit);

				}
			} else if (command.startsWith("article delete ")) {
				String[] delete = command.split(" ");

				int 번호 = Integer.parseInt(delete[2]);

				Article articlefound = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == 번호) {
						articlefound = article;
						break;
					}
				}
				if (articlefound == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", 번호);
				} else {
					articles.remove(articlefound);
					System.out.printf("%d번 게시물이 삭제되었습니다.\n", 번호);
				}
			} else if (command.startsWith("article modify ")) {
				String[] modify = command.split(" ");
				int 번호 = Integer.parseInt(modify[2]);
				Article articlefound = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == 번호) {
						articlefound = article;
						break;
					}
				}
				if (articlefound == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", 번호);
				} else {
					System.out.printf("제목 : ");
					String title2 = sc.nextLine();
					System.out.printf("내용 : ");
					String body2 = sc.nextLine();

					articlefound.modifyArticle(번호, title2, body2, nowdate, nowdate);
					System.out.printf("%d번 글이 수정되었습니다.\n", 번호);
				}
			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body, nowdate, nowdate, count);
				articles.add(article);
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				lastArticleId++;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}

		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}

	public static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		for (int i = 0; i < 3; i++) {

			int 아이디 = lastArticleId + 1;
			String 제목 = "게시물" + 아이디;
			String 내용 = "게시물" + 아이디 + " 내용";
			String 날짜 = Util.getNowDateStr();
			String 업데이트날짜 = Util.getNowDateStr();
			int 조회수 = count;

			Article article = new Article(아이디, 제목, 내용, 날짜, 날짜, 조회수);
			articles.add(article);
			lastArticleId++;
		}
	}
}

class Article {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;
	int hit;

	void modifyArticle(int id, String title, String body, String regDate, String updateDate) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}

	public Article(int id, String title, String body, String regDate, String updateDate, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.body = body;
		this.title = title;
		this.hit = hit;
	}

}