package com.KoreaIT.java.AM.service;

import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.AM.dao.ArticleDao;
import com.KoreaIT.java.AM.dao.MemberDao;

public class MemberService {
private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}
}
