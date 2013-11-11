package com.ccf.action.college;

import java.util.List;
import java.util.Map;

import com.ccf.service.CollegeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListCollegeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6422006616623838603L;
	private CollegeService coService;
	public CollegeService getCoService() {
		return coService;
	}
	public void setCoService(CollegeService coService) {
		this.coService = coService;
	}
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		Map<String, List<?>> request = (Map<String, List<?>>) ActionContext.getContext().get("request");
		request.put("list", this.coService.list());
		return SUCCESS;
	}

}
