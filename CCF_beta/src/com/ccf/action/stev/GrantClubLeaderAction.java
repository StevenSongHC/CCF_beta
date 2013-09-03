package com.ccf.action.stev;

import com.ccf.service.StevService;
import com.opensymphony.xwork2.ActionSupport;

public class GrantClubLeaderAction extends ActionSupport/* implements ServletRequestAware*/ {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3044341302592011273L;
	private StevService service;
	/*private HttpServletRequest request;*/
	private int cid;
	private String newLeaderCode;

	public StevService getService() {
		return service;
	}

	public void setService(StevService service) {
		this.service = service;
	}

	/*@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}*/

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getNewLeaderCode() {
		return newLeaderCode;
	}

	public void setNewLeaderCode(String newLeaderCode) {
		this.newLeaderCode = newLeaderCode;
	}

	@Override
	public String execute() throws Exception {
		this.service.changeLeader(cid, newLeaderCode);
		return SUCCESS;
	}

}
