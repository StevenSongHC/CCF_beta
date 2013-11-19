package com.ccf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class JoinClubAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6153187449718175964L;
	
    private UserService service;
    private int cid;

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@Override
	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);	//session2user
		if (user == null)	//用户未登录时
			return LOGIN;
		if (this.service.verifyLeader(cid, user.getUid()))
			return "leader";
		String command = this.service.tryJoinClub(cid, user.getUid());
		System.out.println(command);
		if (command.equals("харашо")) {
			this.service.joinClub(user.getUid(), cid);
		}
		else
			return command;
		return SUCCESS;
	}
	
}
