package com.ccf.action.user;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3798074748923920569L;
	private User user;
	private UserService service;
	
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
		this.service.updateBasicProfile(user);
		return SUCCESS;
	}
	
	
}
