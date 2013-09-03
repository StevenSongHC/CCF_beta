package com.ccf.action.user;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.ccf.util.UserCookieUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserLoginAction extends ActionSupport implements ServletResponseAware, SessionAware, RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7456574512988425613L;
    public static final String USER_SESSION = "user_session";  
	
	private String account;
	private String password;
	/*private String loginType;*/
	private User user;
	private UserService service;
	private HttpServletResponse response;  
    private Map<String, Object> session; 
	private UserCookieUtil cookieUtil = new UserCookieUtil();	//必须实例化Must instantiated
	private Map<String, Object> requestMap;		//you can replace it with sessionMap
	
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

	/*public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}*/

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
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public UserCookieUtil getCookie() {
		return cookieUtil;
	}

	public void setCookie(UserCookieUtil cookieUtil) {
		this.cookieUtil = cookieUtil;
	}
	
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}
	
	//Sign in
	@Override
	public String execute() throws Exception {
		System.out.println("login action:check u_account " + account);
		
		user = this.service.login(account, password);
		System.out.println("this.account " + account);
		System.out.println("this.password " + password);
		
		//Invalid Account
		if(user == null) {
			System.out.println("!NONE --> INPUT");
			this.addFieldError("account", "Invalid Account");
			return INPUT;
		}
		
		//Wrong Password
		if(account.equals(user.getU_account()) && !password.equals(user.getU_password())) {
			System.out.println("!INPUT");
			this.addFieldError("password", "Wrong Password");
			return INPUT;
		}
		
		//Validate Successful!!!
		if(account.equals(user.getU_account()) && password.equals(user.getU_password())) {
			Cookie cookie = cookieUtil.generateCookie(user);
			response.addCookie(cookie);
			System.out.println("Add cookie succeed,cookie:" + 
					cookie.getName() + "--" + cookie.getValue());
			session.put(USER_SESSION, user);	//add user's info to session
			
			// if user logins by ajax method, then refresh user-box div
			/*if (loginType.equals("ajax")) {
				PrintWriter out = response.getWriter();
				out.append("MSG");
			}*/
			
			// check notification and push them to the request page
			this.service.checkNotification(user.getUid(), requestMap);
			
			return SUCCESS;
		}
		
		System.out.println("!ERROR --> INPUT");
		this.addFieldError("account", "Shit Happened!");
		return INPUT;
	}

	@Override
	public void validate() {
		//Empty Account
		if("".equals(account)) {
			this.addFieldError("account", "Empty account,please type your account!");
			this.addActionError("EEmpty AAcount!");
		}
		//Don't want to show them both at the same time
		//Empty Password
		else if("".equals(password)) {
			this.addFieldError("password", "Empty password is not allowed!");
		}
	}

}
