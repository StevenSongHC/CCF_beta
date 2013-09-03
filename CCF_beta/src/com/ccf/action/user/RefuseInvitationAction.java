package com.ccf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class RefuseInvitationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5828544601781335033L;
	
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
		// 将涉及 notification, invitation 两个表的操作, two tables involved
		System.out.println("from " + from + " to " + to + " where " + where);

		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);
		if(user != null) {
			// update host and guest's notification data, plus invitation's status
			this.service.refuseInvition(Integer.parseInt(from), Integer.parseInt(to), Integer.parseInt(where));
			return SUCCESS;
		}
		return LOGIN;
	}

}
