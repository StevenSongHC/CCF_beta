package com.ccf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class AcceptInvitationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4507332660027086260L;
	
	private String from;
	private String to;
	private String where;
	private UserService service;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	@Override
	public String execute() throws Exception {
		// 将会涉及 notification,invitation,newbie,club,user 5个表的数据修改
		// there will be 5 tables modifies
		System.out.println("from " + from + " to " + to + " where " + where);

		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);
		if(user != null) {
			// update invitation's status, sender(leader) and receiver(newbie)'s notification
			// update club.members and user.u_clubs&u_clubs_level
			this.service.acceptInvition(Integer.parseInt(from), Integer.parseInt(to), Integer.parseInt(where));
			
			return SUCCESS;
		}
		return LOGIN;
	}

}
