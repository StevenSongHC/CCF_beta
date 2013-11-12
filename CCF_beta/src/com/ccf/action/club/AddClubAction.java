package com.ccf.action.club;

import com.ccf.bean.Club;
import com.ccf.service.CityService;
import com.ccf.service.ClubService;
import com.ccf.service.CollegeService;
import com.opensymphony.xwork2.ActionSupport;

public class AddClubAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7541338500050914393L;
	private Club club;
	private ClubService uService;
	private CityService ctService;
	private CollegeService coService;
	
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public ClubService getuService() {
		return uService;
	}
	public void setuService(ClubService uService) {
		this.uService = uService;
	}
	public CityService getCtService() {
		return ctService;
	}
	public void setCtService(CityService ctService) {
		this.ctService = ctService;
	}
	public CollegeService getCoService() {
		return coService;
	}
	public void setCoService(CollegeService coService) {
		this.coService = coService;
	}
	
	public String execute() throws Exception {
		uService.join(club);
		// city.clubAmount +1
		ctService.plusClubAmount(club.getC_city(), 1);
		// college.clubAmount +1
		coService.plusClubAmount(Integer.valueOf(club.getC_college()), 1);
		return SUCCESS;
	}
	
	
	
}
