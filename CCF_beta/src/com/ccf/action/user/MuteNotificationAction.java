package com.ccf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class MuteNotificationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4949353620678958415L;
	
	private UserService service;
	private String type;
	private String sender;
	private String receiver;
	private String date;
	private String cid;
	
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
		if(user != null) {
			
			if (type.equals("invitation-reply")) {	// if the type of notification you want to mute is invitation reply
				if (sender == null || receiver == null || cid == null)
					return "error";
				this.service.muteInvitationReplyInNotification(Integer.parseInt(sender), Integer.parseInt(receiver),
										Integer.parseInt(cid), date);
			}
				
			if (type.equals("newbie-notice")) {
				if (sender == null || receiver == null || cid == null)
					return "error";
				this.service.muteNewbieNoticeInNotification(Integer.parseInt(sender), Integer.parseInt(receiver),
										Integer.parseInt(cid));
			}
			
			if (type.equals("join-club-reply")) {
				if (sender == null || receiver == null || cid == null)
					return "error";
				this.service.muteJoinClubApplyNoticeInNotification(Integer.parseInt(sender), Integer.parseInt(receiver),
										Integer.parseInt(cid));
			}
				
			
			return SUCCESS;
		}
		return LOGIN;
	}
	
}
