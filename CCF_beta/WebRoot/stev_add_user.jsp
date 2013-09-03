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
	
	<link rel="stylesheet" type="text/css" href="css/index.css">

  </head>
  
  <body>
    
    <s:include value="/user-box.jsp" />
    
	<h1>Register</h1>
	
	<center>
		<s:form action="addUser">
		<s:textfield name="user.u_college" label="User College"></s:textfield>
  		<s:textfield name="user.u_name" label="User Name"></s:textfield>
  		<s:textfield name="user.u_account" label="Account"></s:textfield>
  		<s:password name="user.u_password" label="Password"></s:password>
  		<%-- <s:password name="user.password" label="Confirm Password"></s:password> --%>
  		<s:textfield name="user.u_code" label="Give it a code"></s:textfield>
  		<s:textfield name="user.u_pic" label="Portrait" value="images/author_default.png" />
  		<%-- <s:file name="user.u_pic" accept="text/html,text/plain" label="Portrait" value="images/author_default.png"/> --%>
  		<s:textfield name="user.u_summary" label="Bio" value="Just For Fun" />
  		<s:textfield name="user.u_homepage" label="Home Page" value="index.jsp" />
  		<s:textfield name="user.u_clubs" label="Club" value="Skull" />
  		<s:textfield name="user.u_clubs_level" label="Club Position" value="1@boss" />
  		<s:textfield name="user.u_xp" label="Active Point" value="99" />
  		<s:submit value="Register"></s:submit>
  	</s:form>
	</center>


  </body>
</html>
