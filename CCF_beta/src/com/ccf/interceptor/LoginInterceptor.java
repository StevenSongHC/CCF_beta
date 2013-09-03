package com.ccf.interceptor;

import com.ccf.action.user.UserLoginAction;
import com.ccf.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6001836831419411256L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/*user = (User)invocation.getInvocationContext().get(com.ccf.action.user.UserLoginAction.USER_SESSION);*/	//Null Pointer
		Object action = invocation.getAction();
		if(action instanceof UserLoginAction) {	//对login action不做拦截Do not intercept login action
			System.out.println("It's login action!");
			return invocation.invoke();
		}
		ActionContext ctx = invocation.getInvocationContext();
		java.util.Map<String, Object> session = ctx.getSession();
		User user = (User)session.get(com.ccf.action.user.UserLoginAction.USER_SESSION);
		if(user == null)
			return "denied";
		System.out.println("login intercepter:" + user.getU_name());
		System.out.println("login interceptor:have a logged user");
		return invocation.invoke();
	}
	
}
