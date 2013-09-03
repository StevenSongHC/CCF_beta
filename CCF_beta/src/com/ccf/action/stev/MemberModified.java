package com.ccf.action.stev;

import com.ccf.bean.Club;
import com.ccf.service.StevService;
import com.opensymphony.xwork2.ActionSupport;

public class MemberModified extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1914668520101256659L;
	
	private Club club;
	private StevService service;
	
	public Club getClub() {
		return club;
	}
	
	public void setClub(Club club) {
		this.club = club;
	}
	
	public StevService getService() {
		return service;
	}
	
	public void setService(StevService service) {
		this.service = service;
	}

	public String execute() throws Exception {
		
		
		club.getC_members();
		this.service.memberModified(club.getCid(), club.getC_members(), club.getC_code_edit_authority_members(),
										club.getC_code_current_leader());
		return SUCCESS;
	}

}
