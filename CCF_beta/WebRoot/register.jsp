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
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">
	

  </head>
  
  <body>
    
	<h1>Register</h1>
	
	<center>
		<s:form action="register">
		<s:textfield name="college" label="User College" value=""></s:textfield>
  		<s:textfield name="username" label="*User Name"></s:textfield>
  		<s:textfield name="account" label="*Account"></s:textfield>
  		<s:password name="password" label="*Password(6-20)"></s:password>
  		<s:password name="password_cfm" label="*Confirm Password"></s:password>
  		<s:textfield name="summary" label="Bio"/>
  		<s:submit value="Register"></s:submit>
  		</s:form>
  	<s:property value="error_username"/>
  	<s:property value="error_password" />
	</center>


  </body>
</html>
