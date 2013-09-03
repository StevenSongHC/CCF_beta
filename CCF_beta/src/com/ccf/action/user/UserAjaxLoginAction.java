package com.ccf.action.user;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.ccf.util.UserCookieUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserAjaxLoginAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1636151258997444783L;

	private String account;
	private String password;
	private int status;			// 1=success, 2=invalid account, 3=wrong password, 4=error
	private String msg;
	private String data;
	private User user;
	private UserService service;
    private Map<String, Object> session; 
	private UserCookieUtil cookieUtil = new UserCookieUtil();	// for cookie generated using
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public UserCookieUtil getCookieUtil() {
		return cookieUtil;
	}
	public void setCookieUtil(UserCookieUtil cookieUtil) {
		this.cookieUtil = cookieUtil;
	}
	
	@Override
	public String execute() throws Exception {
        
		System.out.println("from ajax: account :" + account + " , password: " + password);
		user = this.service.login(account, password);
		
		// Invalid Account
		if(user == null) {
			status = 2;
			msg = "Invalid Account, plz check it out";
		}
		
		// Wrong Password
		else if(account.equals(user.getU_account()) && !password.equals(user.getU_password())) {
			status = 3;
			msg = account;		// save account content
		}
				
		// Validate Successful!!!
		else if(account.equals(user.getU_account()) && password.equals(user.getU_password())) {
			Cookie cookie = cookieUtil.generateCookie(user);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.addCookie(cookie);
			
			System.out.println("Add cookie succeed,cookie:" + 
					cookie.getName() + "--" + cookie.getValue());
			// add user's info to session
			session.put(com.ccf.action.user.UserLoginAction.USER_SESSION, user);
			
			status = 1;
			msg = "<a href=\"homepage\">" + user.getU_name() + 
					"</a> | <a href=\"logout\">log out(totally)</a>";
		}
		else {
			status = 0;
			msg = "Ooops! SHIT HAPPEND";
		}
		System.out.println("MSG:" + msg + " status" + status);
		data = "{status:" + status + ",msg:'" + msg + "'}";
		System.out.println(data);
		return "map";
	}
	
}
