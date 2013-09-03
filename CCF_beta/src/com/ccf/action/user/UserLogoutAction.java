package com.ccf.action.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class UserLogoutAction extends ActionSupport implements 
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -288644758799827435L;
    private HttpSession session;
    private HttpServletRequest request;  
	private HttpServletResponse response; 
    
	public void setSession(HttpSession session) {
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

	@Override
	public String execute() throws Exception {
		Cookie[] cookies = request.getCookies();
		/*removing user session first
		in case that user can't logout when cookie's time running out*/
		session = request.getSession(false); 
		if(session != null) {
			session.removeAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);
			System.out.println("remove session succeed");
		}
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(com.ccf.util.UserCookieUtil.USER_COOKIE)) {
				cookie.setValue("");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				System.out.println("cookie " + cookie.getName() + "has been removed succeed");
			}
		}
		return SUCCESS;
	}

}
