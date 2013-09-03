package com.ccf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class ReplyJoinClubAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6058495623839343627L;
	
	private UserService service;
	
	private String action;
	private String sender;
	private String receiver;
	private String cid;

	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}

	@Override
	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);
		if (user == null)
			return LOGIN;
		
		if (action == null || cid == null || sender == null || receiver == null)
			return "null";
		
		if (!this.service.verifyLeader(Integer.parseInt(cid), user.getUid()))	// if current user isn't the leader
			return LOGIN;
		
		
		// sender means applicant, why? cuz that's him sent out this join_club_apply
		if (action.equals("approve")) {
			// let that guy be a member first
			this.service.addANewMember(Integer.parseInt(cid), Integer.parseInt(sender));
			// then send a newbie-message to every old member, update relative's notification
			this.service.approveJoinClubApply(Integer.parseInt(cid), Integer.parseInt(sender));
			return SUCCESS;
		}
			
		if (action.equals("reject")) {
			this.service.rejectJoinClubApply(Integer.parseInt(cid), Integer.parseInt(sender));
			return SUCCESS;
		}
		return ERROR;
	}
	

}
