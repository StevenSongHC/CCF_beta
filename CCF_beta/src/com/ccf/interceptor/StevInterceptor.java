package com.ccf.interceptor;

import com.ccf.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class StevInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3614429467981814250L;
	public static final String SUPERMAN = "stev";
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		java.util.Map<String, Object> session = ctx.getSession();
		User user = (User)session.get(com.ccf.action.user.UserLoginAction.USER_SESSION);
		if(!user.getU_name().equals(SUPERMAN))	//if validate stev id failed
			return "denied";
		return invocation.invoke();
	}

}
