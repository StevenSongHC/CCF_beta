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
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">

  </head>
  
  <body>	用js进行account与password的是否为空判断(以后)
    <center>
	<s:form action="login">
		<s:textfield name="account" label="Account" value="" />
		<s:password name="password" label="Password" value="" />
		<s:submit value="sign in" />
	</s:form>
	Something Wrong this Login Action
	<%-- <s:fielderror key="keyaccount" /> --%>
	</center>
  </body>
</html>
