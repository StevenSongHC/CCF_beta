package com.ccf.action.province;

import java.util.List;
import java.util.Map;

import com.ccf.service.ProvinceService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListProvinceAction extends ActionSupport {
	
	private static final long serialVersionUID = 8460097337672091470L;
	
	private ProvinceService prService;

	public ProvinceService getPrService() {
		return prService;
	}
	public void setPrService(ProvinceService prService) {
		this.prService = prService;
	}
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		Map<String, List<?>> request = (Map<String, List<?>>) ActionContext.getContext().get("request");
		request.put("list", this.prService.list());
		return SUCCESS;
	}
	

}
