package com.ccf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserProfileAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 545815400821105741L;
	private UserService service;
	private User user;
	private String account;

	public UserService getService() {
		return service;
	}
	
	public void setService(UserService service) {
		this.service = service;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String execute() throws Exception {
		if (account == null) {	
			System.out.println("Default Setting Action");
			HttpSession session=ServletActionContext.getRequest().getSession();
			User user = (User) session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);
			if(user == null)
				return INPUT;
			System.out.println("Default account is " + user.getU_account());
			account = user.getU_account();
		}
		this.user = this.service.findUserByAccount(account);
		return SUCCESS;
	}

}
