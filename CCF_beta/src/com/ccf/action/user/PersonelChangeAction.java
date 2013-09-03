package com.ccf.action.user;

import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class PersonelChangeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6795726093724829019L;
	
	private int cid;
	private String account;
	private String job;
	private UserService service;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	@Override
	public String execute() throws Exception {
		if (!this.service.verifyLeader(cid, this.service.getCurrentUser().getUid()))
			return LOGIN;
		
		if (job == null)
			return "emptyOption";
		
		System.out.println("cid:" + cid + " account:" + account + " job:" + job);
		
		return SUCCESS;
	}

}
