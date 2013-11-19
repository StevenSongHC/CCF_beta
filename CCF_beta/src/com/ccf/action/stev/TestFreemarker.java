package com.ccf.action.stev;

import java.io.IOException;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.ccf.util.TestFreemarkerUtil;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.SimpleHash;
import freemarker.template.TemplateException;

public class TestFreemarker extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -559318467757038037L;
	private UserService service;
	private User user;
	private String htmlPage;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}
	
	public String getHtmlPage() {
		return htmlPage;
	}

	@Override
	public String execute() throws Exception {
		TestFreemarkerUtil fm = new TestFreemarkerUtil(); 
		user = this.service.findUserByAccount("654826249");
		SimpleHash map = new SimpleHash();
		map.put("user", user);
		
		String htmlFile = "testFreemarker.html";
		
		try {
			fm.init("test.ftl", htmlFile, map, "templates");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
		this.htmlPage = htmlFile;
		return SUCCESS;
	}

}
