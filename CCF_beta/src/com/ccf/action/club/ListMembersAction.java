package com.ccf.action.club;

import com.ccf.service.ClubService;
import com.opensymphony.xwork2.ActionSupport;

public class ListMembersAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8742550036886252244L;
	private ClubService service;
	
	public ClubService getService() {
		return service;
	}
	
	public void setService(ClubService service) {
		this.service = service;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}	//delete this action if necessary
	
}
