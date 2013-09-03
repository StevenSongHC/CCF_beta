package com.ccf.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LeaderAuthorityInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2513508594998155740L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		java.util.Map<String, Object> session = ctx.getSession();
		User user = (User)session.get(com.ccf.action.user.UserLoginAction.USER_SESSION);
		if (user == null)
			return "login";
		
		//注入Spring 中 userService，insert the userService
		ActionContext actionContext = invocation.getInvocationContext();    
		ServletContext context = (ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);    
		ApplicationContext ctxx = WebApplicationContextUtils.getWebApplicationContext(context); 
		UserService service =(UserService) ctxx.getBean("userService");
		service.verifyLeader(1, user.getUid());
		return "denied";
	}

}
