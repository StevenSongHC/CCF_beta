<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!-- user navigator model,insert in anywhere -->	
    <div id="user">
    <%-- <s:if test="#session.user_session!=null"><s:a href="homepage?account=%{#session.user_session.u_account}">${user_session.u_name}</s:a> | <s:a href="logout">log out(totally)</s:a></s:if> --%>
    <s:if test="#session.user_session!=null"><s:a href="homepage">${user_session.u_name}</s:a> | <s:a href="logout">log out(totally)</s:a></s:if>
    <s:else><span id="login"><s:a href="#login">log in</s:a></span> | <s:a href="register.jsp">register</s:a></s:else>
    </div>
