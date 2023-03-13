package com.KoreaIT.java.AM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	private static String getnowdata() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = formatter.format(now);
		return nowdate;
	}

}
