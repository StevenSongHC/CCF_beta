package com.ccf.util;

import java.util.Random;

public class UserCodeUtil {
	
	public String generateUserCode() {
		int len = 7;					
		String str = "0123456789";
		Random random = new Random();
		StringBuffer code = new StringBuffer();
		for(int i=0;i<len;i++) {
			int gen = random.nextInt(10);	
			code.append(str.charAt(gen));
		}
		//以后要加上从数据库检索有无重复code的发法
		return code.toString();
	}
	
}
