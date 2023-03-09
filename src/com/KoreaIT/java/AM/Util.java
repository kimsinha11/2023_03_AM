package com.KoreaIT.java.AM;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Util {
	
	public static String getNowDateStr() {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = formatter.format(now);
		return nowdate;
	}
	
}
