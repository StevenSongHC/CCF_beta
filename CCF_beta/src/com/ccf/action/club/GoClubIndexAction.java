package com.ccf.action.club;

import com.ccf.bean.Club;
import com.ccf.service.ClubService;
import com.opensymphony.xwork2.ActionSupport;

public class GoClubIndexAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 210680493565060077L;
	private ClubService service;
	private Club club;
	
	public ClubService getService() {
		return service;
	}
	
	public void setService(ClubService service) {
		this.service = service;
	}
	
	public Club getClub() {
		return club;
	}
	
	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public String execute() throws Exception {
		this.club = this.service.findClub(club.getCid());
		return SUCCESS;
	}
	
	
}
