package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.service.ArticleService;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController extends Controller {

	private Scanner sc;
	private List<Article> articles;

	private String command;
	private String actionMethodName;
	private ArticleService articleService;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		articleService = Container.articleService;

	}

	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;

		switch (actionMethodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
		default:
			System.out.println("다시 입력해주세요.");
			break;
		}
	}

	public void doWrite() {
//중요한 데이터를 컨트롤러가 아니라 dao에서 일하도록 
		int id = articleService.setNewId();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		String regDate = Util.getNowDateStr();
		String updateDate = "";

		Article article = new Article(id, loginedMember.id, title, body, regDate, updateDate);
		articleService.add(article);

		System.out.printf("%d번 게시물이 등록되었습니다.\n", id);

	}

	private void showList() {

		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> forPrintArticles = articleService.getforPrintArticles(searchKeyword);

		if (forPrintArticles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}

		System.out.println(" 번호  //   제목    //  작성자  //         작성날짜          //  조회수  ");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			String writer = null;

			List<Member> members = Container.memberDao.members;
			Article article = forPrintArticles.get(i);

			for (int j = 0; j < members.size(); j++) {
				Member member = members.get(j);
				if (member.id == article.memberId) {
					writer = member.name;

				}
			}
			System.out.printf("  %d   //   %s   //   %s  //   %s   //  %d  \n", article.id, article.title, writer,
					article.regDate, article.hit);
		}
	}

	private void showDetail() {
		String detail[] = command.split(" ");
		int id = Integer.parseInt(detail[2]);
		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		String writer = null;

		List<Member> members = Container.memberDao.members;

		for (int j = 0; j < members.size(); j++) {
			Member member = members.get(j);
			if (member.id == foundArticle.memberId) {
				writer = member.name;

			}
		}
		foundArticle.hit++;
		System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n작성자 : %s\n등록날짜 : %s\n수정날짜 : %s\n조회수 : %d\n", foundArticle.id,
				foundArticle.title, foundArticle.body, writer, foundArticle.regDate, foundArticle.updateDate,
				foundArticle.hit);
	}

	private void doDelete() {
		String delete[] = command.split(" ");
		int id = Integer.parseInt(delete[2]);
		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
			return;
		} else if (foundArticle.memberId == loginedMember.id) {
			articleService.remove(foundArticle);
			System.out.printf("%d번 게시물이 삭제\n", id);
		} else {
			System.out.println("권한이 없습니다.");
			return;
		}
	}

	private void doModify() {
		String modify[] = command.split(" ");
		int id = Integer.parseInt(modify[2]);
		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
			return;
		} else if (foundArticle.memberId == loginedMember.id) {
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.printf("내용 : ");
			String body = sc.nextLine();
			String updateDate = Util.getNowDateStr();

			foundArticle.title = title;
			foundArticle.body = body;
			foundArticle.updateDate = updateDate;

			System.out.printf("%d번 글이 수정되었습니다.\n", id);
		} else {
			System.out.println("권한이 없습니다.");
			return;
		}
	}

	public void maketestdata() {
		System.out.println("==게시물 테스트 데이터 생성==");
		articleService.add(new Article(1, 3, "제목1", "제목1", Util.getNowDateStr(), "", 11));
		articleService.add(new Article(2, 1, "제목2", "제목2", Util.getNowDateStr(), "", 22));
		articleService.add(new Article(3, 2, "제목3", "제목3", Util.getNowDateStr(), "", 33));

	}
}