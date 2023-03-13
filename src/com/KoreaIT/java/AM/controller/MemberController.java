package com.KoreaIT.java.AM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController {
	List<Member> members;
	private Scanner sc;

	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}

	public void doJoin() {


		int id = members.size() + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = "";

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

		Member member = new Member(id, name, loginId, loginPw, regDate, updateDate);
		members.add(member);

		System.out.printf("%s님 회원가입 되었습니다.\n", name);
	}

	public void showList() {
		if (members.size() == 0) {
			System.out.println("등록된 회원이 없습니다.");
		} else {
			System.out.printf("번호  |이름    |아이디     |가입날짜     \n");
			for (int i = members.size() - 1; i >= 0; i--) {
				Member memberlist = members.get(i);

				System.out.printf("%d    |%s   |%s    |%s  \n", memberlist.id, memberlist.name, memberlist.loginId,
						memberlist.regDate);

			}
		}
	}

	public boolean getMemberById(String loginId) {
		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	
}
