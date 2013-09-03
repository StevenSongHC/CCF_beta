package com.ccf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class PersonalPageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2858696286010465907L;
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
	public String execute() throws Exception {	//用来处理用户个人页面及信息的action处理，需验证是否为当前账户,verify user identify required
		if (account == null) {	//default setting action,self update
			/*session = request.getSession(false);
			if(session == null)
				return INPUT;*/
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
