package com.ccf.action.user;

import net.sf.json.JSONArray;

import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class GetUserNameAjaxAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6977867174238145804L;

	private String ids;
	private UserService service;
	private String result;
	 
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("ids:" + ids);
		result = JSONArray.fromObject(service.findUsersInfoByUids(ids)).toString();
		return super.execute();
	}

}
