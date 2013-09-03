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
    
    <title>My JSP 'testautologin.jsp' starting page</title>
    
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
    login successful ${user.uid}<br>
    
    <b>check session
    
    </b>
    
    <center>user account is ${user.u_account}</center>
    <% 
    Cookie cookies[]=request.getCookies();  
    out.println(" cookie's amount is："+ cookies.length); 
      out.println("<br>"+"<br>"); 
    for(int i = 0;i<cookies.length;i++){ 
      out.println("getName="+cookies[i].getName()+"<br>");  
      out.println("getValue="+cookies[i].getValue()+"<br>");  
      out.println("getComment="+cookies[i].getComment()+"<br>");  
      out.println("getDomain="+cookies[i].getDomain()+"<br>"); 
      out.println("getPath="+cookies[i].getPath()+"<br>"); 
      out.println("getMaxAge="+cookies[i].getMaxAge()+"<br>"); 
      out.println("getVersion="+cookies[i].getVersion()+"<br>"); 
      out.println("getSecure="+cookies[i].getSecure()+"<br>"); 
      out.println("<br>"+"<br>"); 
    } 
    %> 
  </body>
</html>
