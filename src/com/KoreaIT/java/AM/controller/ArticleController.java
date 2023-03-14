package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController extends Controller {

	private Scanner sc;
	private List<Article> articles;

	private String command;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articles = new ArrayList<>();

	}

	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;

		switch (actionMethodName) {
		case "write":
			if (isLogined() == false) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			doWrite();

			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "delete":
			if (isLogined() == false) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			doDelete();
			break;
		case "modify":
			if (isLogined() == false) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			doModify();
			break;
		default:
			System.out.println("다시 입력해주세요.");
			break;
		}
	}

	int lastarticleid = 3;

	public void doWrite() {

		int id = lastarticleid + 1;
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		String regDate = Util.getNowDateStr();
		String updateDate = "";

		Article article = new Article(id, loginedMember.id, title, body, regDate, updateDate);
		articles.add(article);

		System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
		lastarticleid++;
	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}

		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> forPrintArticles = articles;

		if (searchKeyword.length() > 0) {
			System.out.println("searchKeyword : " + searchKeyword);
			forPrintArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}
			if (forPrintArticles.size() == 0) {
				System.out.println("검색 결과가 없습니다");
				return;
			}
		}

		System.out.println(" 번호  //   제목    //  작성자  //         작성날짜          //  조회수  ");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			System.out.printf("  %d   //   %s   //   %d  //   %s   //  %d  \n", article.id, article.title,
					article.memberId, article.regDate, article.hit);
		}
	}

	public void showDetail() {
		String detail[] = command.split(" ");
		int id = Integer.parseInt(detail[2]);
		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		else {
			foundArticle.hit++;
			System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n작성자 : %d\n등록날짜 : %s\n수정날짜 : %s\n조회수 : %d\n", foundArticle.id,
					foundArticle.title, foundArticle.body, foundArticle.memberId, foundArticle.regDate,
					foundArticle.updateDate, foundArticle.hit);
		}
	}

	public void doDelete() {
		String delete[] = command.split(" ");
		int id = Integer.parseInt(delete[2]);
		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 존재하지 않습니다.", id);
			return;
		} else {
			articles.remove(foundArticle);
			System.out.printf("%d번 게시물이 삭제\n", id);

		}
	}

	public void doModify() {
		String modify[] = command.split(" ");
		int id = Integer.parseInt(modify[2]);
		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
			return;
		} else {
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.printf("내용 : ");
			String body = sc.nextLine();
			String updateDate = Util.getNowDateStr();

			foundArticle.title = title;
			foundArticle.body = body;
			foundArticle.updateDate = updateDate;

			System.out.printf("%d번 글이 수정되었습니다.\n", id);
		}
	}

	public Article getArticleById(int id) {

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return article;
			}
		}

		return null;
	}

	public void maketestdata() {
		System.out.println("==게시물 테스트 데이터 생성==");
		articles.add(new Article(1, 3, "제목1", "제목1", Util.getNowDateStr(), "", 11));
		articles.add(new Article(2, 1, "제목2", "제목2", Util.getNowDateStr(), "", 22));
		articles.add(new Article(3, 2, "제목3", "제목3", Util.getNowDateStr(), "", 33));

	}
}
