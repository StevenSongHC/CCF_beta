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
    
    <title>My JSP 'user_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/index.css">
	

  </head>
  
  <body>
    
    <s:include value="/user-box.jsp" />
    
    <center><h1><font color="red">Current Registered Users List</font></h1></center>
	
	<table border="1" width="75%" align="center">
	
	<tr> 
		<td>UID</td>
		<td>User Name</td>
		<td>Account</td>
		<td>PWD</td>
		<td>College</td>
		<td>Code</td>
		<td>Portrait Link</td>
		<td>Summary</td>
		<td>User's Home Page</td>
		<td>Clubs</td>
		<td>Clubs Level</td>
		<td>XP</td>
		<td>view detail</td>
		<td>modify</td>
	</tr>
	
	<s:iterator value="#request.list" id="user">
	
		<tr>
			<td>
				<s:property value="#user.uid" />
			</td>
			<td>
				<s:property value="#user.u_name" />
			</td>
			<td>
				<s:property value="#user.u_account" />
			</td>
			<td>
				<s:property value="#user.u_password" />
			</td>
			<td>
				<s:property value="#user.u_college" />
			</td>
			<td>
				<s:property value="#user.u_code" />
			</td>
			<td>
				<s:property value="#user.u_pic" />
			</td>
			<td>
				<s:property value="#user.u_summary" />
			</td>
			<td>
				<s:property value="#user.u_homepage" />
			</td>
			<td>
				<s:property value="#user.u_clubs" />
			</td>
			<td>
				<s:property value="#user.u_clubs_level" />
			</td>
			<td>
				<s:property value="#user.u_xp" />
			</td>
			<td><s:a href="profile?account=%{#user.u_account}">profile summary</s:a></td>
			<td><s:a href="setting?account=%{#user.u_account}">modify</s:a></td>
		</tr>
	
	</s:iterator>
	
	</table>

  </body>
</html>
