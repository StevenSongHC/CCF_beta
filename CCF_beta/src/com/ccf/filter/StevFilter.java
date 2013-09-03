package com.ccf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;

import com.ccf.bean.User;
import com.ccf.service.StevService;

public class StevFilter implements Filter {

	private StevService service;
	private HttpSession session;
	private User user;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("stev Filter invoked!");
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    session = req.getSession(true);
	    if(session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION) == null ||
	    		session == null)
	    	//In this web site,req.getContextPath() --> /CCF_beta 
	    	res.sendRedirect(req.getContextPath() + "/access_denied.jsp");	//wrong session,then go access denied
	    else {
	    	user = (User) session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);
	    	if(service.checkIsStev(user))
	    		chain.doFilter(request, response);
	    	else
	    		res.sendRedirect(req.getContextPath() + "/access_denied.jsp");
	    }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext sc = filterConfig.getServletContext();  
		WebApplicationContext wac = (WebApplicationContext) sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);  
		this.service = (StevService)wac.getBean("stevService"); 
	}

}
