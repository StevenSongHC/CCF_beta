package com.ccf.action.club;

import com.ccf.service.ClubService;
import com.opensymphony.xwork2.ActionSupport;

public class ClubLeaderPageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3694201927483385130L;
	
	private ClubService service;
	private int cid;
	public ClubService getService() {
		return service;
	}
	public void setService(ClubService service) {
		this.service = service;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	@Override
	public String execute() throws Exception {
		
		return super.execute();
	}

}
