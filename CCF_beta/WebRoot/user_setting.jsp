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
    
    <title>My JSP 'user_setting.jsp' starting page</title>
    
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
    
	<s:form  action="update">
		<s:textfield name="user.u_name" label="User Name" value="%{user.u_name}"></s:textfield>
		<s:textfield name="user.u_college" label="User College" value="%{user.u_college}"></s:textfield>
  		
  		<%-- <s:password name="user.password" label="Confirm Password"></s:password> --%>
  		<s:textfield name="user.u_pic" label="Portrait" value="%{user.u_pic}" />
  		<%-- <s:file name="user.u_pic" accept="text/html,text/plain" label="Portrait" value="images/author_default.png"/> --%>
  		
  		<s:textfield name="user.u_summary" label="Bio" value="%{user.u_summary}" />
  		<s:textfield name="user.u_clubs" label="User's Clubs(c_id)" value="%{user.u_clubs}" />
  		<s:textfield name="user.u_clubs_level" label="Club Position" value="%{user.u_clubs_level}" />
  		<s:hidden name="user.uid" value="%{user.uid}" />
		<%-- 
		  		<s:textfield name="user.u_clubs" label="Club" value="%{user.u_clubs}" />
  		<s:textfield name="user.u_clubs_level" label="Club Position" value="%{user.u_clubs_level}" />
		<s:hidden name="user.u_account" value="%{user.u_account}" />
		<s:hidden name="user.u_code" value="%{user.u_code}" />
		<s:hidden name="user.u_homepage" value="%{user.u_homepage}" /> 
		 --%>
		<s:submit value="Update"></s:submit>
	</s:form>

  </body>
</html>
