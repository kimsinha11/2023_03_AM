package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller {
	List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;

	private Member loginedMember;

	int lastMemberId = 0;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	public void doAction(String actionMethodName, String command) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "list":
			showList();
			break;
		default:
			System.out.println("해당 기능은 사용할 수 없습니다");
			break;
		}
	}

	private void doLogin() {
		System.out.print("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.print("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		// 얘 있나? (사용자가 입력한 아이디랑 일치하는 회원이 우리한테 있나?)

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("일치하는 회원이 없습니다");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다");
			return;
		}

		loginedMember = member;
		System.out.printf("로그인 성공! %s님 반갑습니다\n", loginedMember.name);
	}

	private void doJoin() {
		int id = lastMemberId + 1;
		String regDate = Util.getNowDateStr();
		String loginId = null;
		while (true) {
			System.out.print("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.println("이미 사용중인 아이디입니다");
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.print("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.print("로그인 비밀번호 확인: ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 확인해주세요");
				continue;
			}
			break;
		}

		System.out.print("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 가입되었습니다\n", id);
		lastMemberId++;
	}
	public void showList() {
		if (members.size() == 0) {
			System.out.println("등록된 회원이 없습니다.");
		} else {
			System.out.printf("번호  |이름     |아이디      |가입날짜     \n");
			for (int i = members.size() - 1; i >= 0; i--) {
				Member memberlist = members.get(i);

				System.out.printf("%d    |%s   |%s    |%s  \n", memberlist.id, memberlist.name, memberlist.loginId,
						memberlist.regDate);

			}
		}
	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public void maketestdata2() {
		System.out.println("==회원 테스트 데이터 생성==");
		members.add(new Member(1, "user1", "userId1", "userPW1", Util.getNowDateStr(), ""));
		members.add(new Member(2, "user2", "userId2", "userPW2", Util.getNowDateStr(), ""));
		members.add(new Member(3, "user3", "userId3", "userPW3", Util.getNowDateStr(), ""));

	}
}