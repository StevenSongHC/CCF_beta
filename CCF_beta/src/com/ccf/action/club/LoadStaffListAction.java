package com.ccf.action.club;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoadStaffListAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6154588652033948996L;
	private UserService uService;
	private String leader;
	private String publisher;
	private String member;
	private String result;

	public UserService getuService() {
		return uService;
	}
	public void setuService(UserService uService) {
		this.uService = uService;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String execute() throws Exception {
		
		// transform uid string array to User entity array
		List<Map<String, Object>> leaders = uService.findUsersInfoByUids(leader);
		List<Map<String, Object>> publishers = uService.findUsersInfoByUids(publisher);
		List<Map<String, Object>> members = uService.findUsersInfoByUids(member);
		// second decoration, creating the title
		for (Map<String, Object> obj : leaders) {
			obj.put("title", "Leader");
		}
		for (Map<String, Object> obj : publishers) {
			obj.put("title", "Publisher");
		}
		for (Map<String, Object> obj : members) {
			obj.put("title", "Member");
		}
		
		// merge
		leaders.addAll(publishers);
		leaders.addAll(members);
		
		// return data
		System.out.println(JSONArray.fromObject(leaders).toString());
		result = JSONArray.fromObject(leaders).toString();
		return super.execute();
	}

}
