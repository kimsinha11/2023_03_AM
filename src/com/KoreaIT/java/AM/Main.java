package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Article> articles = new ArrayList<>();
	static List<Member> members = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		maketestdata();
		Scanner sc = new Scanner(System.in);
		int lastarticleid = 3;
		int lastmemberid = 0;
		while (true) {
			System.out.printf("명령어 >> ");
			String command = sc.nextLine().trim();

			if (command.equals("exit")) {
				break;
			}
			if (command.equals("article write")) {
				int id = lastarticleid + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String regDate = Util.getNowDateStr();
				String updateDate = "";

				Article article = new Article(id, title, body, regDate, updateDate);
				articles.add(article);

				System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
				lastarticleid++;

			} else if (command.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
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
						continue;
					}
				}

				System.out.println(" 번호  //  제목    //   작성날짜   //  조회수  ");
				for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
					Article article = forPrintArticles.get(i);
					System.out.printf("  %d   //   %s   //    %s   //  %d  \n", article.id, article.title,
							article.regDate, article.hit);
				}

			} else if (command.startsWith("article detail")) {
				String detail[] = command.split(" ");
				int id = Integer.parseInt(detail[2]);
				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
//				for (int i = 0; i < articles.size(); i++) {
//					Article article = articles.get(i);
//					if (article.id == id) {
//						foundArticle = article;
//						break;
//					}
//				}

				else {
					foundArticle.hit++;
					System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n등록날짜 : %s\n수정날짜 : %s\n조회수 : %d\n", foundArticle.id,
							foundArticle.title, foundArticle.body, foundArticle.regDate, foundArticle.updateDate,
							foundArticle.hit);

				}
			} else if (command.startsWith("article delete")) {
				String delete[] = command.split(" ");
				int id = Integer.parseInt(delete[2]);
				Article foundArticle = getArticleById(id);

//				for (int i = 0; i < articles.size(); i++) {
//					Article article = articles.get(i);
//					if (article.id == id) {
//						foundArticle = article;
//						break;
//					}
//				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.", id);
					continue;
				} else {
					articles.remove(foundArticle);
					System.out.printf("%d번 게시물이 삭제\n", id);

				}

			} else if (command.startsWith("article modify")) {
				String modify[] = command.split(" ");
				int id = Integer.parseInt(modify[2]);
				Article foundArticle = getArticleById(id);

//				for (int i = 0; i < articles.size(); i++) {
//					Article article = articles.get(i);
//					if (article.id == id) {
//						foundArticle = article;
//						break;
//					}
//				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.", id);
					continue;
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
			} else if (command.equals("member join")) {
				int id = members.size() + 1;
				String regDate = Util.getNowDateStr();
				String loginId = null;
				while (true) {
					System.out.printf("로그인 아이디 : ");
					loginId = sc.nextLine();

					if (getMemberById(loginId) == false) {
						System.out.printf("%s는(은) 이미 사용 중인 아이디 입니다.\n", loginId);
						continue;
					}
					break;
				}

				String loginPw = null;
				String loginPwCf = null;
				while (true) {
					System.out.printf("로그인 비밀번호 : ");
					loginPw = sc.nextLine();
					System.out.printf("로그인 비밀번호 확인 : ");
					loginPwCf = sc.nextLine();

					if (loginPw.equals(loginPwCf) == false) {
						System.out.println("비밀번호를 다시 입력해주세요.");
						continue;
					}
					break;
				}

				System.out.printf("이름 : ");
				String name = sc.nextLine();

				Member member = new Member(id, name, loginId, loginPw, regDate);
				members.add(member);

				System.out.printf("%s님 회원가입 되었습니다.\n", name);

			} else if (command.equals("member list")) {
				if (members.size() == 0) {
					System.out.println("등록된 회원이 없습니다.");
				} else {
					System.out.printf("번호  |이름    |아이디     |가입날짜     \n");
					for (int i = members.size() - 1; i >= 0; i--) {
						Member memberlist = members.get(i);

						System.out.printf("%d    |%s   |%s    |%s  \n", memberlist.id, memberlist.name,
								memberlist.loginId, memberlist.regDate);

					}
				}
			}

			else {
				System.out.println("존재하지 않는 명령입니다.");
			}
		}
		System.out.println("==프로그램 종료==");

	}

	public static boolean getMemberById(String loginId) {
		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	
	public static Article getArticleById(int id) {

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return article;
			}
		}

		return null;
	}

	static void maketestdata() {
		System.out.println("==게시물 테스트 데이터 생성==");
		articles.add(new Article(1, "제목1", "제목1", Util.getNowDateStr(), "", 11));
		articles.add(new Article(2, "제목2", "제목2", Util.getNowDateStr(), "", 22));
		articles.add(new Article(3, "제목3", "제목3", Util.getNowDateStr(), "", 33));

	}

}

class Article {
	int id;
	String title;
	String body;
	String regDate;
	String updateDate;
	int hit;

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

class Member {
	int id;
	String name;
	String loginId;
	String loginPw;
	String regDate;

	public Member(int id, String name, String loginId, String loginPw, String regDate) {
		this.id = id;
		this.name = name;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.regDate = regDate;

	}

}