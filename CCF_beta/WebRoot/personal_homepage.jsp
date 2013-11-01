<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>This's "personal_homepage"</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/index.css">
	

    </head>
  
  <body>
  
  	<div id="user">
    <s:if test="#session.user_session!=null"><s:a href="homepage">${user_session.u_name}</s:a> | <s:a href="logout">log out(totally)</s:a></s:if>
    <s:else><s:a href="autologin">log in</s:a> | <s:a href="register.jsp">register</s:a></s:else>
    </div>
    
	current link:<s:property value="%{user.u_homepage}"/>
	<center><h3>This is <s:property value="%{user.u_name}"/>'s(mine) Homepage</h3></center>
	<br><hr><br>
	<center>My College:<s:property value="%{user.u_college}"/></center><br>
	<center><img src="<s:property value="%{user.u_pic}"/>" /></center><br>
	<center>My Summary:<s:property value="%{user.u_summary}"/></center><br>
	<!-- user's club info -->
	<s:iterator value="#request.clublist" id="clubinfo">
	<ul><s:a href="homepage/myclub/%{#clubinfo.cid}"><s:property value="#clubinfo.clubname"/></s:a> (<s:property value="#clubinfo.jobname"/>)</ul>
	</s:iterator>
	
	<s:a href="setting">Setting My Staff</s:a>
	
  </body>
</html>