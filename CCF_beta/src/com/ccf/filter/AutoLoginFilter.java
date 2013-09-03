package com.ccf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.ccf.util.UserCookieUtil;

public class AutoLoginFilter implements Filter {

	 /*private Map<String, Object> session; */
	private HttpSession session;
	private User user;
	private UserService service;;
	private UserCookieUtil cookieUtil = new UserCookieUtil();
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("AutoLoginFilter works");
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    session = req.getSession(true); 	//Don't set it false,or external browser will get null pointer exception
	    //Whenever come in,if session is null,try to login by detecting cookie
        if (session == null || session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION) == null) {
        	Cookie cookies[] = req.getCookies();
        	if(cookies != null) {
        		for(Cookie cookie : cookies) {
            		System.out.println("Got the cookie:" + cookie.getName());
    				if(cookie.getName().equals(com.ccf.util.UserCookieUtil.USER_COOKIE)) {
    					System.out.println("And already existed cookie is " + cookie.getName());
    					System.out.println("already existed cookie's account " + 
    							cookie.getValue().split(",")[0] + cookie.getValue().split(",")[1]);
    					//increase cookie's maxage
    					cookie.setMaxAge(com.ccf.util.UserCookieUtil.USER_COOKIE_MAXAGE);
    					res.addCookie(cookie);
    					//get the user by cookie
    					user = service.login(cookie.getValue().split(",")[0], cookie.getValue().split(",")[1]);
    					System.out.println("user_session add:" + user.getU_name());
    					//then put it to session
    					if(user != null)
    						session.setAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION, user);
    					else
    						chain.doFilter(request, response);	//login wrong
    				}
            	}
        		chain.doFilter(request, response);	//no such name session
        		return;
        	}
        	chain.doFilter(request, response);
        }
        else {
        	System.out.println("straight to the heaven");
        	user = (User) session.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);
        	Cookie cookie = cookieUtil.generateCookie(user);
        	res.addCookie(cookie);
        	chain.doFilter(request, response);	//if session existed,then keep going
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//在Filter的init方法中获得WebApplicationContext并调用getBean(String)方法,从而可以使用service
		ServletContext sc = filterConfig.getServletContext();  
		WebApplicationContext wac = (WebApplicationContext) sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);  
		this.service = (UserService)wac.getBean("userService"); 
	}
	
}
