package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class App {
	static List<Article> articles;
	static List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {

		System.out.println("==프로그램 시작==");
		maketestdata();
		maketestdata2();

		Scanner sc = new Scanner(System.in);
		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		while (true) {
			System.out.printf("명령어 >> ");
			String command = sc.nextLine().trim();

			if (command.equals("exit")) {
				break;
			}
			if (command.equals("member join")) {
				memberController.doJoin();
			} else if (command.equals("member list")) {
				memberController.showList();
			} else if (command.equals("article write")) {
				articleController.doWrite();

			} else if (command.startsWith("article list")) {
				articleController.showList(command);
			}

			else if (command.startsWith("article detail")) {
				articleController.showDetail(command);

			} else if (command.startsWith("article delete")) {
				articleController.doDelete(command);

			} else if (command.startsWith("article modify")) {
				articleController.doModify(command);
			}

			else {
				System.out.println("존재하지 않는 명령입니다.");
			}
		}
		System.out.println("==프로그램 종료==");
	}

	static void maketestdata() {
		System.out.println("==게시물 테스트 데이터 생성==");
		articles.add(new Article(1, "제목1", "제목1", Util.getNowDateStr(), "", 11));
		articles.add(new Article(2, "제목2", "제목2", Util.getNowDateStr(), "", 22));
		articles.add(new Article(3, "제목3", "제목3", Util.getNowDateStr(), "", 33));

	}

	static void maketestdata2() {
		System.out.println("==회원 테스트 데이터 생성==");
		members.add(new Member(1, "user1", "userId1", "userPW1", Util.getNowDateStr(), ""));
		members.add(new Member(2, "user2", "userId2", "userPW2", Util.getNowDateStr(), ""));
		members.add(new Member(3, "user3", "userId3", "userPW3", Util.getNowDateStr(), ""));

	}

}
