package com.ccf.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.ccf.bean.User;
import com.ccf.service.UserService;

public class UserCookieUtil {
	public static final String USER_COOKIE = "user_cookie";
	public static final int USER_COOKIE_MAXAGE = 60;
	private UserService service;
	
	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	//Generate a new cookie
	public Cookie generateCookie(User user) {  
		 System.out.println("Into user generate_cookie");
		 
		 /*Cookie cookie =  new Cookie(USER_COOKIE, user.getUid() + "," + user.getU_account() + 
				 ","  + user.getU_password() + "," + user.getU_code()); */ 
		 Cookie cookie = new Cookie(USER_COOKIE, user.getU_account() + "," + user.getU_password()
				 + "," + user.getU_code());
		 cookie.setMaxAge(USER_COOKIE_MAXAGE);
		 /*cookie.setPath("/");*/	//用来跨应用调cookie用，可删
	     System.out.println("已生成cookie,value:" + cookie.getValue()); 
	     return cookie;
	}  

	
	//Log out
	public Cookie removeCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();  
	    if (cookies != null) {  
		    for (Cookie cookie : cookies) {  
			    if (USER_COOKIE.equals(cookie.getName())) {  
				    cookie.setValue("");  
				    cookie.setMaxAge(0);  
				    System.out.println("cookie:" + cookie.getName() + "removed succeed");
				    return cookie;  
			    }  
		    }  
	    }  
	return null;  
	}
	
}
