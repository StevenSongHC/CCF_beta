package com.ccf.action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class AjaxTestAction extends ActionSupport implements ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1636151258997444783L;

	private HttpServletResponse response;  
	private UserService service;
   
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	@Override
	public String execute() throws Exception {
		System.out.println("Have Some Problems?");
		PrintWriter out = response.getWriter();
        response.setCharacterEncoding("utf-8");
        out.print("Ajax");
        out.close();
        return null;
        //return SUCCESS? whatever
	}
	
}
