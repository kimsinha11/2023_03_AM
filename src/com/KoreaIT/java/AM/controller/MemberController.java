package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.AM.dao.MemberDao;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.service.MemberService;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller {

	private Scanner sc;
	private String command;
	private String actionMethodName;
	private MemberService memberService;



	public MemberController(Scanner sc) {
		memberService = Container.memberService;
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
		case "profile":
			showProfile();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("해당 기능은 사용할 수 없습니다");
			break;
		}
	}

	private void showProfile() {

		System.out.println("==현재 로그인한 회원정보==");
		System.out.printf("나의 회원번호 : %d\n", loginedMember.id);
		System.out.printf("로그인 아이디 : %s\n", loginedMember.loginId);
		System.out.printf("로그인 이름 : %s\n", loginedMember.name);

	}

	private void doLogout() {

		loginedMember = null;
		System.out.println("로그아웃");
	}

	private void doLogin() {
		String loginId = null;
		String loginPw = null;
		while (true) {
			System.out.print("로그인 아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			break;
		}

		while (true) {
			System.out.print("로그인 비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}
			break;
		}

		// 얘 있나? (사용자가 입력한 아이디랑 일치하는 회원이 우리한테 있나?)

		Member member = memberService.getMemberByLoginId(loginId);

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
		if (isLogined() != false) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		System.out.println("**회원가입 필수정보 : 아이디, 비밀번호, 이름**");
	
		int id = memberService.setNewId();

		String regDate = Util.getNowDateStr();

		String loginId = null;
		while (true) {
			System.out.print("로그인 아이디 : ");
			loginId = sc.nextLine();
			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}

			if (memberService.isJoinableLoginId(loginId) == false) {
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

			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			System.out.print("로그인 비밀번호 확인: ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 확인해주세요");
				continue;
			}
			break;

		}

		String name = null;
		while (true) {
			System.out.print("이름 : ");
			name = sc.nextLine();
			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요.");
				continue;
			}
			break;
		}

		Member member = new Member(id, name, loginId, loginPw, regDate);
		memberService.add(member);

		System.out.printf("%d번 회원이 가입되었습니다\n", id);

	}

	public void showList() {
		if (memberService.getMembers().size() == 0) {
			System.out.println("등록된 회원이 없습니다.");
		} else {
			System.out.printf("번호  |이름     |아이디      |가입날짜     \n");
			for (int i = memberService.getMembers().size() - 1; i >= 0; i--) {
				Member memberlist = memberService.getMembers().get(i);

				System.out.printf("%d    |%s   |%s    |%s  \n", memberlist.id, memberlist.name, memberlist.loginId,
						memberlist.regDate);

			}
		}
	}

	

	public void maketestdata() {
		System.out.println("==회원 테스트 데이터 생성==");
		memberService.add(new Member(1, "user1", "a", "a", Util.getNowDateStr()));
		memberService.add(new Member(2, "user2", "b", "b", Util.getNowDateStr()));
		memberService.add(new Member(3, "user3", "c", "c", Util.getNowDateStr()));

	}
}