package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller{
	List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	
	public MemberController(Scanner sc) {
		this.members =new ArrayList<>();
		this.sc = sc;
	}
	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;
		
		switch(actionMethodName) {
		case "join":
			doJoin();
			break;
		case "list":
			showList();
			break;
		case "login":
			doLogin();
			break;
		default:
			System.out.println("다시 입력해주세요.");
			break;
		}
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
			System.out.printf("번호  |이름     |아이디      |가입날짜     \n");
			for (int i = members.size() - 1; i >= 0; i--) {
				Member memberlist = members.get(i);

				System.out.printf("%d    |%s   |%s    |%s  \n", memberlist.id, memberlist.name, memberlist.loginId,
						memberlist.regDate);

			}
		}
	}
	
	public void doLogin() {

		String loginId = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
				if (getMemberById(loginId) != false) {
					System.out.printf("존재하지 않는 아이디입니다.\n");
					break;
				} else {
				
			}

		}
		
		String loginPw = null;

		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();

			if(getMemberByPw(loginPw) != false){
				System.out.println("비밀번호가 틀렸습니다.\n");
				continue;
			}
			break;
		}
		System.out.printf("%s 로그인 성공\n", loginId);
	}
	
	public boolean getMemberById(String loginId) {
		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}
	public boolean getMemberByPw(String loginPw) {
		for (Member member : members) {

			if (member.loginPw.equals(loginPw)) {
				return false;
			}
		}
		return true;
	}

	public void maketestdata2() {
		System.out.println("==회원 테스트 데이터 생성==");
		members.add(new Member(1, "user1", "userId1", "userPW1", Util.getNowDateStr(), ""));
		members.add(new Member(2, "user2", "userId2", "userPW2", Util.getNowDateStr(), ""));
		members.add(new Member(3, "user3", "userId3", "userPW3", Util.getNowDateStr(), ""));

	}

}
