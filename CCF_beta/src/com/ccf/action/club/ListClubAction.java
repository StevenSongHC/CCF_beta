package com.ccf.action.club;

import java.util.List;
import java.util.Map;

import com.ccf.service.ClubService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListClubAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6066354867419549560L;
	private ClubService service;

	public ClubService getService() {
		return service;
	}

	public void setService(ClubService service) {
		this.service = service;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Map<String, List<?>> request = (Map<String, List<?>>) ActionContext.getContext().get("request");
		request.put("list", this.service.list());	//List<User> type
		return SUCCESS;
	}
	
}
