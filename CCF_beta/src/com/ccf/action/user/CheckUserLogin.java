package com.ccf.action.user;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class CheckUserLogin extends ActionSupport implements 
		ServletRequestAware, ServletResponseAware, SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7187772202121499766L;
    private HttpServletRequest request; 
	private HttpServletResponse response;
    private Map<String, Object> session; 
    private User user;
    private UserService service;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	@Override
	public String execute() throws Exception {
		//try to login by searching existed cookie first
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				System.out.print("Got the cookie:" + cookie.getName());
				if(cookie.getName().equals(com.ccf.util.UserCookieUtil.USER_COOKIE)) {
					System.out.println("And already existed cookie is " + cookie.getName());
					System.out.println("already existed cookie's account " + 
							cookie.getValue().split(",")[0]);
					//increase cookie's duration once login
					increaseCookieDuration(cookie);
					//cookie.getValue().split(",")[0] --> account contained in cookie
					return autoLogin(cookie.getValue().split(",")[0]);
				}
			}
		}
		return INPUT;
	}
	
	//push user's session into the current page
	public String autoLogin(String account) {
		//fetch the user's account from cookie,then find user's info from database
		user = service.findUserByAccount(account);
		if(user != null) {
			session.put(com.ccf.action.user.UserLoginAction.USER_SESSION, user);
			return SUCCESS;
		}
		return INPUT;
	}
	
	//increase cookie's life
	public void increaseCookieDuration( Cookie cookie) {
		cookie.setMaxAge(com.ccf.util.UserCookieUtil.USER_COOKIE_MAXAGE);
		response.addCookie(cookie);	//update cookie
	}

}
