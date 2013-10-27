package com.ccf.action.city;

import java.util.List;
import java.util.Map;

import com.ccf.service.CityService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListCityAction extends ActionSupport {

	private static final long serialVersionUID = 154109108451707614L;
	private CityService ctService;
	
	public CityService getCtService() {
		return ctService;
	}
	public void setCtService(CityService ctService) {
		this.ctService = ctService;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		Map<String, List<?>> request = (Map<String, List<?>>) ActionContext.getContext().get("request");
		request.put("list", this.ctService.list());
		return SUCCESS;
	}
	
}
