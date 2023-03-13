package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Post> posts = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		maketestdata();
		Scanner sc = new Scanner(System.in);
		int lastpostid = 3;

		while (true) {
			System.out.printf("명령어 >> ");
			String command = sc.nextLine().trim();

			if (command.equals("exit")) {
				break;
			}
			if (command.equals("post write")) {
				int id = lastpostid + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String regDate = "";
				String updateDate = "";
				Post post = new Post(id, title, body, regDate, updateDate);
				posts.add(post);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				lastpostid++;

			} else if (command.equals("post list")) {
				if (posts.size() == 0) {
					System.out.println("게시글이 없습니다.");
				} else {
					System.out.printf("번호   / 제목    \n");

					for (int i = posts.size() - 1; i >= 0; i--) {
						Post post = posts.get(i);

						System.out.printf("%d  / %s   \n", post.id, post.title);
					}
				}
			} else if (command.startsWith("post detail")) {
				String detail[] = command.split(" ");
				int id = Integer.parseInt(detail[2]);

				Post foundpost = null;

				for (int i = 0; i > posts.size(); i++) {
					Post post = posts.get(i);
					if (post.id == id) {
						foundpost = post;
					}
				}
				if (foundpost == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
				} else {
					System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n날짜 : %s\n", foundpost.id, foundpost.title,
							foundpost.body, foundpost.regDate);
				}
			} else if (command.startsWith("post delete")) {
				String delete[] = command.split(" ");
				int id = Integer.parseInt(delete[2]);

				Post foundpost = null;

				for (int i = 0; i > posts.size(); i++) {
					Post post = posts.get(i);
					if (post.id == id) {
						foundpost = post;
					}
				}
				if (foundpost == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
				} else {
					posts.remove(foundpost);
					System.out.printf("%d번 게시물 삭제되었습니다.\n", id);
				}
			} else if (command.startsWith("post modify")) {
				String modify[] = command.split(" ");
				int id = Integer.parseInt(modify[2]);

				Post foundpost = null;

				for (int i = 0; i > posts.size(); i++) {
					Post post = posts.get(i);
					if (post.id == id) {
						foundpost = post;
					}
				}
				if (foundpost == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
				} else {
					System.out.printf("제목 : ");
					String title = sc.nextLine();
					System.out.printf("내용 : ");
					String body = sc.nextLine();
					String updateDate = "";

					foundpost.title = title;
					foundpost.body = body;
					foundpost.updateDate = updateDate;

					System.out.printf("%d번 게시물 수정되었습니다.\n", id);
				}
			}

			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
		System.out.println("==프로그램 종료==");

	}

	static void maketestdata() {
		System.out.println("==테스트를 위한 데이터를 생성합니다.==");
		posts.add(new Post(1, "제목1", "내용1", "", "", 11));
		posts.add(new Post(2, "제목2", "내용2", "", "", 22));
		posts.add(new Post(3, "제목3", "내용3", "", "", 33));

	}

}

class Post {
	int id;
	String title;
	String body;
	String regDate;
	String updateDate;
	int hit;

	public Post(int id, String title, String body, String regDate, String updateDate) {
		this(id, title, body, regDate, updateDate, 0);
	}

	public Post(int id, String title, String body, String regDate, String updateDate, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.hit = hit;
	}

	void hitup() {
		this.hit++;
	}

}