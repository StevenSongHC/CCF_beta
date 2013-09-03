package com.ccf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class InviteJoinClubAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7549099055957102014L;
	
	private UserService service;
	private int cid;
	private String account;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String execute() throws Exception {
		if (cid == 0 || account ==null)
			return ERROR;
		
		System.out.println("have the account:" + account + " cid:" + cid);
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		User host = (User) session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);
		
		if (host == null)
			return LOGIN;
		
		if (!this.service.verifyLeader(cid, host.getUid())) {	//the host is not the leader of this club
			this.addFieldError("account", "U r not the leader,SUCKER");
			return "limited";	//that's mean authority limited, it's U r not the fucking leader
		}
		
		User guest = this.service.findUserByAccount(account);
		
		if (guest == null) {	//invalid account
			this.addFieldError("account", "No Such User Exist!");
			System.out.println("invite result no user existed");
			return "reinvite";
		}
		
		if (guest.getUid() == host.getUid())
			return "sb";	// don't invite yourself
		
		if (this.service.tryInviteNewMember(account, cid).equals("OK")) {
			this.service.invite(cid, host.getUid(), guest.getUid());
			return SUCCESS;
		}
		System.out.println(this.service.tryInviteNewMember(account, cid));
		return this.service.tryInviteNewMember(account, cid);
	}
	
}
