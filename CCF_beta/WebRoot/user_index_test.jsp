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
    
    <title>My JSP 'user_index_test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
	current link:<s:property value="%{user.u_homepage}"/>
	<center><h3>Welcome to <s:property value="%{user.u_name}"/>'s Zone</h3></center>
	<br><hr><br>
	<center>My College:<s:property value="%{user.u_college}"/></center><br>
	<center><img src="<s:property value="%{user.u_pic}"/>" /></center><br>
	<center>My Summary:<s:property value="%{user.u_summary}"/></center><br>
	<%-- <s:if test="%{user.u_clubs}!=null"> --%>
	<ul>
		<li><s:property value="%{user.u_clubs}" />:<s:property value="%{user.u_clubs_level}"/>
	</ul>
	<%-- </s:if> --%>

  </body>
</html>
