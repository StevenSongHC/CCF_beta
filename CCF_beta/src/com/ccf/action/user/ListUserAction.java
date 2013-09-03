package com.ccf.action.user;

import java.util.List;
import java.util.Map;

import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListUserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4962334092141194269L;
	private UserService service;

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Map<String, List<?>> request = 
				(Map<String, List<?>>) ActionContext.getContext().get("request");
		request.put("list", this.service.fetchUsersList());
		return SUCCESS;
	}
	
}
