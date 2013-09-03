package com.ccf.action.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.ccf.bean.User;
import com.ccf.service.ClubService;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class MyClubAction extends ActionSupport implements RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4110342457644690316L;
	
	private UserService userService;
	private ClubService clubService;
	private String cid;
	private String job;
	private String destination;
	Map<String, Object> requestMap;

	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public ClubService getClubService() {
		return clubService;
	}
	public void setClubService(ClubService clubService) {
		this.clubService = clubService;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}

	public String execute() throws Exception {
		User user = userService.getCurrentUser();
		
		if (user == null)
			return "login";
		
		job = this.userService.userJob(user.getUid(), Integer.parseInt(cid));
		System.out.println("job:" + job);
		
		Map<String, Object> details = new HashMap<String, Object>();
		
		if (job.equals("free"))
			destination = "goClubIndex?club.cid=" + cid;
		
		if (job.equals("leader")) {
			Map<String, Object> clubOriginalData = clubService.clubLeaderDetails(Integer.parseInt(cid));
			System.out.println("clubDetails:" + clubOriginalData.toString());
			
			details.put("cid", clubOriginalData.get("cid"));
			details.put("name", clubOriginalData.get("name"));
			details.put("pic", clubOriginalData.get("pic").toString());
			details.put("url", clubOriginalData.get("url").toString());
			details.put("member", clubOriginalData.get("member").toString());
			details.put("intro", clubOriginalData.get("intro").toString());
			details.put("activity", clubOriginalData.get("activity").toString());
			details.put("file", clubOriginalData.get("file").toString());
			details.put("xp", clubOriginalData.get("xp").toString());
			details.put("province", clubOriginalData.get("province").toString());
			details.put("city", clubOriginalData.get("city").toString());
			details.put("college", clubOriginalData.get("college").toString());
			details.put("tag", clubOriginalData.get("tag").toString());
			
			// return publishers' nad leader's username instead of uid by default
			// PS: show members's username by Ajax
			details.put("publisher", this.userService.findUsersInfoByUids(clubOriginalData.get("publisher").toString()));
			details.put("leader", this.userService.findUsersInfoByUids(clubOriginalData.get("leader").toString()));
			
			System.out.println("Dtails:" + details.toString());
			
			requestMap.put("clubinfo", details);
			destination = "/myclub_leader.jsp";
		}
			
		
		if (job.equals("member"))
			destination = "/myclub_member.jsp";
		
		if (job.equals("killer"))
			destination = "/myclub_member.jsp";
		
		return job;			// go to different type of page depends on user's job in the club
	}
	
}