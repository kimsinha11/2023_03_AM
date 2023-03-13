package com.KoreaIT.java.AM.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Util {
	//포맷팅 현재 날짜/시간 반환 String타입
	public static String getNowDateStr() {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = formatter.format(now);
		return nowdate;
	}
	
}