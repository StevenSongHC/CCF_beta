package com.ccf.action.user;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class HomePageAction extends ActionSupport implements RequestAware {

	/** @author stev
	 *  @date   2013/07/11
	 *  @using  user enter-club action
	 */
	private static final long serialVersionUID = -3316021870228345214L;
	private UserService service;
	private User user;
	private String account;
	private Map<String, Object> requestMap;/*
	private HttpServletRequest request;  
	private HttpSession session;*/

	public UserService getService() {
		return service;
	}
	
	public void setService(UserService service) {
		this.service = service;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Override
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}


	@Override
	public String execute() throws Exception {	//用来处理用户个人页面及信息的action处理，需验证是否为当前账户,verify user identify required
		if (account == null) {	//default setting action without account paramount, self update
			/*session = request.getSession(false);
			if(session == null)
				return INPUT;*/
			System.out.println("Default Setting Action");
			user = this.service.getCurrentUser();
			// not login yet
			if(user == null)
				return INPUT;
			System.out.println("Default account is " + user.getU_account());
		}
		else {
			user = this.service.findUserByAccount(account);
		}
		
		// push user's club info
		List<Map<String, Object>> list = this.service.getUserClubList(user.getUid());
		System.out.println("clublist:" + list);
		requestMap.put("clublist", list);
		
		// just let me through
		if (this.service.getCurrentUser().getU_name().equals(com.ccf.interceptor.StevInterceptor.SUPERMAN)) {
			System.out.println("It's stev, let me through");
			return SUCCESS;
		}
			
		
		// if it's other user's homepage
		// besides, u_code is not proper to use now, cuz there's a numbers of users' code are the same
		if (this.service.getCurrentUser().getUid() != user.getUid())
			return "visitor";
		
		return SUCCESS;
	}

}
