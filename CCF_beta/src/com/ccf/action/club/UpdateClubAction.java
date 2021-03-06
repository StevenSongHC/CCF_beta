package com.ccf.action.club;

import com.ccf.bean.Club;
import com.ccf.service.ClubService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateClubAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6956857605061458513L;
	private Club club;
	private ClubService service;
	
	public Club getClub() {
		return club;
	}
	
	public void setClub(Club club) {
		this.club = club;
	}
	
	public ClubService getService() {
		return service;
	}
	
	public void setService(ClubService service) {
		this.service = service;
	}

	@Override
	public String execute() throws Exception {
		this.service.updateBasicInfo(club);
		return SUCCESS;
	}

}
