package com.ccf.action.stev;

import com.ccf.bean.User;
import com.ccf.service.StevService;
import com.opensymphony.xwork2.ActionSupport;

public class AddTestUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3479207703623309873L;
	private User user;
	private StevService service;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public StevService getService() {
		return service;
	}

	public void setService(StevService service) {
		this.service = service;
	}

	@Override
	public String execute() throws Exception {
		this.service.addTestUser(this.user);
		return SUCCESS;
	}
	
}
